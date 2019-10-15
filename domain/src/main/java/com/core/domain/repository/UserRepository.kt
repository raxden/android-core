package com.core.domain.repository

import com.core.commons.Resource
import com.core.domain.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository : Repository {

    fun retrieve(username: String): Single<User>

    suspend fun retrieveCO(username: String): Resource<User>
}