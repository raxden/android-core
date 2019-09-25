package com.core.data.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.core.domain.Resource

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<ResultType, RequestType> {

    private lateinit var result: Flowable<Resource<ResultType>>

    init {
        val source: Flowable<Resource<ResultType>>

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
}