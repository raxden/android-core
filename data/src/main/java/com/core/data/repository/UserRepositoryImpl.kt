package com.core.data.repository

import com.core.commons.Resource
import com.core.data.network.gateway.AppGateway
import com.core.domain.User
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class UserRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway,
        @Named("io") private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun retrieve(username: String) = withContext(dispatcher) {
        try {
            Resource.success(gateway.user(username).toUser())
        } catch (e: Exception) {
            Resource.error(e, null)
        }
    }

    /*
    val response = gateway.userCO(username)
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            return Resource.success(body.toUser())
        }
    }
    return Resource.error(
            IOException("Error getting comments ${response.code()} ${response.message()}"),
            null
    )
    */
}