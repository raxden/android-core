package com.core.data.repository

import com.core.domain.User
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepositoryImpl @Inject internal constructor() : UserRepository {

    override fun login(username: String, password: String): Single<User> = Single
            .timer(3000, TimeUnit.MILLISECONDS)
            .map { User(username = username) }

    override fun retrieve(): Single<User> = Single
            .just(User(username = "raxden"))
}