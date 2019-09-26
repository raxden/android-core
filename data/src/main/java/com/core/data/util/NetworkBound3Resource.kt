package com.core.data.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.core.commons.Resource
import io.reactivex.Completable

import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class NetworkBound3Resource<ResultType, RequestType>(
        private val emitter: FlowableEmitter<Resource<ResultType>>
) {

    private lateinit var result: Flowable<Resource<ResultType>>

    init {
        val compositeDisposable = CompositeDisposable()

        emitter.onNext(Resource.loading(null))

        networkObservable()
                .subscribe()
                .addTo(compositeDisposable)

        diskObservable()
                //.distinctUntilChanged()
                .subscribe { emitter.onNext(Resource.success(it)) }
                .addTo(compositeDisposable)

        emitter.setDisposable(compositeDisposable)
    }

    fun asFlowable(): Flowable<Resource<ResultType>> = result

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Single<RequestType>

    private fun networkObservable() = loadFromDb().take(1)
            .flatMapCompletable { dbData ->
                emitter.onNext(Resource.loading(dbData))
                if (shouldFetch(dbData))
                    createCall()
                            .observeOn(Schedulers.computation())
                            .doOnError { throwable ->
                                emitter.onNext(Resource.error(throwable, dbData))
                            }
                            .doOnSuccess { data ->
                                printData("saveCallResult", data)
                                saveCallResult(data)
                            }
                            .ignoreElement()
                else
                    Completable.complete()
            }
            .observeOn(Schedulers.io())
            .doOnComplete { printData("networkObservable") }

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