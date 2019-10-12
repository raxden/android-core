package com.core.data.repository

import com.core.data.network.gateway.AppGateway
import com.core.domain.User
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway
) : UserRepository {

    override fun retrieve(username: String): Single<User> = gateway
            .user(username)
            .map { it.toUser() }
}