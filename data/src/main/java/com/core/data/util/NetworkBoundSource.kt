package com.core.data.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread

import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class NetworkBoundSource<ResultType: Any, RequestType: Any>(
        emitter: FlowableEmitter<ResultType>
) {

    private var data: ResultType? = null


    init {
        Timber.d("Preparing to load data from DB... ${Thread.currentThread().name}")
        val dbDisposable = loadFromDb()
                .map {
                    printData("Data loaded from DB, prepared to send.", it)
                    data = it
                    it
                }
                //.subscribeOn(Schedulers.trampoline())
                //.observeOn(Schedulers.trampoline())
                .distinctUntilChanged()
                .subscribe {
                    printData("Sending DB data to subscriber.", it)
                    emitter.onNext(it)
                }

        Timber.d("Preparing to load data from Server... ${Thread.currentThread().name}")
//        if (shouldFetch(data)) {
            val networkDisposable = createCall()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribeWith(object : DisposableSingleObserver<RequestType>() {
                        override fun onSuccess(t: RequestType) {
                            printData("Data loaded from Server, prepared to persist in DB.", t)
                            saveCallResult(t)
                        }

                        override fun onError(e: Throwable) {
                            emitter.onError(e)
                        }
                    })
//        } else {
//            Timber.d("cancel fetch")
//        }
    }

    private fun printData(message: String, data: Any) {
        Timber.d("$message - ${Thread.currentThread().name}")
        (data as? List<*>)?.let { list->
            if (list.isEmpty()) {
                Timber.d("$message: EMPTY")
            } else {
                list.forEach { item ->
                    Timber.d("$message $item")
                }
            }
        } ?: Timber.d("$message $data")
    }

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Single<RequestType>
}