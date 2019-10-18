package com.core.data.repository

import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.data.remote.AppGateway
import com.core.data.repository.mapper.UserDataMapper
import com.core.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val mapper: UserDataMapper,
    @Named("io") private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun retrieve(username: String) = withContext(dispatcher) {
        when (val result = gateway.user(username)) {
            is Result.Success -> Resource.success(mapper.transform(result.data))
            is Result.Error -> Resource.error(result.exception)
        }
    }
}