package com.core.data.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread

import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class NetworkBoundSource<ResultType, RequestType>(
        emitter: FlowableEmitter<ResultType>
) {

    private var data: ResultType? = null

    init {
        val dbDisposable = loadFromDb()
                .map {
                    data = it
                    it
                }
                .distinctUntilChanged()
                .subscribe { consumer ->
                    emitter.onNext(consumer)
                }

//        if (shouldFetch(data)) {
            val networkDisposable = createCall()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribeWith(object : DisposableSingleObserver<RequestType>() {
                        override fun onSuccess(t: RequestType) {
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

    private fun printData(message: String, data: List<RequestType>) {
        Timber.d(message)
        if (data.isEmpty()) {
            Timber.d("$message: EMPTY")
        } else {
            data.forEach { project ->
                Timber.d("$message $project")
            }
        }
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