package com.core.domain.repository

import com.core.domain.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository : Repository {

    fun retrieve(id: Long): Single<User>

    fun save(user: User) : Single<User>
}