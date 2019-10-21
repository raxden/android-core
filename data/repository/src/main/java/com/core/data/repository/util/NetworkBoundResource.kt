package com.core.data.repository.util

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.common.android.Resource
import com.core.common.android.Result
import kotlinx.coroutines.*
import timber.log.Timber
import java.lang.Exception
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            Timber.d("NetworkBoundResource starting...")
            result.value = Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    fetchFromNetwork(dbResult)
                } catch (e: Exception) {
                    Timber.e("An error happened: $e")
                    setValue(Resource.error(e, loadFromDb()))
                }
            } else {
                Timber.d("Return data from local database")
                setValue(Resource.success(dbResult))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    // ---

    private suspend fun fetchFromNetwork(dbResult: ResultType) {
        Timber.d("Fetch data from network")
        setValue(Resource.loading(dbResult)) // Dispatch latest value quickly (UX purpose)
        when (val result = createCall()) {
            is Result.Success -> {
                Timber.d("Data fetched from network")
                saveCallResult(result.data)
                setValue(Resource.success(loadFromDb()))
            }
            is Result.Error -> {
                Timber.e("An error happened: ${result.exception}")
                setValue(Resource.error(result.exception, loadFromDb()))
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Timber.d("Resource: $newValue")
        //if (result.value != newValue) result.postValue(newValue)
        result.value = newValue
    }

    @WorkerThread
    protected abstract suspend fun saveCallResult(data: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract suspend fun createCall(): Result<RequestType>
}