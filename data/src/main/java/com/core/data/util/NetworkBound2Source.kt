package com.core.data.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Completable

import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

abstract class NetworkBound2Source<ResultType : Any, RequestType : Any>() {

    private val result: Flowable<ResultType>

    init {
        printData("start...")

        result = Flowable.merge(
                networkObservable(),
                diskObservable()
        )
                .map {
                    printData("resultObservable", it)
                    it
                }
                .distinctUntilChanged()
    }

    fun asFlowable() = result

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Single<RequestType>

    private fun networkObservable() = loadFromDb().take(1)
            .flatMap { dbData ->
                if (shouldFetch(dbData)) {
                    createCall()
                            .observeOn(Schedulers.computation())
                            .doOnSuccess { data ->
                                printData("saveCallResult", data)
                                saveCallResult(data)
                            }
                            .toFlowable()
                            .flatMap { loadFromDb().take(1) }
                } else {
                    Flowable.just(dbData)
                }
            }
            .doOnNext { printData("networkObservable", it) }

    private fun diskObservable() = loadFromDb()
            .doOnNext { printData("diskObservable", it) }

    private fun printData(message: String, data: Any? = null) {
        val threadName = Thread.currentThread().name
        (data as? List<*>)?.let { list ->
            if (list.isEmpty())
                Timber.d("[$threadName]: $message - empty list ")
            else
                Timber.d("[$threadName]: $message - items(${list.size}) ")
        } ?: Timber.d("[$threadName]: $message ")
    }
}