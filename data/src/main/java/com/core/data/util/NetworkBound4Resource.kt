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
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class NetworkBound4Resource<ResultType, RequestType>(
        private val emitter: FlowableEmitter<Resource<ResultType>>
) {

    init {
        val compositeDisposable = CompositeDisposable()

        emitter.onNext(Resource.loading(null))

        loadFromDbOneTime()
                .flatMapCompletable { dbData ->
                    emitter.onNext(Resource.loading(dbData))
                    if (shouldFetch(dbData)) {
                        createCall()
                                .observeOn(Schedulers.computation())
                                .doOnSuccess { saveCallResult(it) }
                                .doOnError { emitter.onNext(Resource.error(it, dbData)) }
                                .ignoreElement()
                    } else {
                        emitter.onNext(Resource.success(dbData))
                        Completable.complete()
                    }
                }
                .subscribeBy(
                        onError = {
                            Timber.d("error")
                        },
                        onComplete = {
                            loadFromDb()
                                    .distinctUntilChanged()
                                    .doOnNext { emitter.onNext(Resource.success(it)) }
                                    .subscribe()
                                    .addTo(compositeDisposable)
                        }
                )
                .addTo(compositeDisposable)

        emitter.setDisposable(compositeDisposable)
    }

    @WorkerThread
    protected abstract fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Single<RequestType>

    private fun loadFromDbOneTime() = loadFromDb().take(1)

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