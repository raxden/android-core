package com.core.domain.repository

import com.core.domain.User
import io.reactivex.Single

interface UserRepository : Repository {

    fun login(username: String): Single<User>

    fun retrieve(): Single<User>
}