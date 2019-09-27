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

abstract class NetworkBoundResource<ResultType, RequestType>(
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
                                .ignoreElement()
                    } else {
                        emitter.onNext(Resource.success(dbData))
                        Completable.complete()
                    }
                }
                .subscribeBy(
                        onError = { throwable ->
                            loadFromDb()
                                    .distinctUntilChanged()
                                    .doOnNext { emitter.onNext(Resource.error(throwable, it)) }
                                    .subscribe()
                                    .addTo(compositeDisposable)
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
}