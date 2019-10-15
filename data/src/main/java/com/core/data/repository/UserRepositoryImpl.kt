package com.core.data.repository

import com.core.commons.Resource
import com.core.data.network.gateway.AppGateway
import com.core.domain.User
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway
) : UserRepository {

    override fun retrieve(username: String): Single<User> = gateway
            .user(username)
            .map { it.toUser() }

    override suspend fun retrieveCO(username: String): Resource<User> {
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
    }
}