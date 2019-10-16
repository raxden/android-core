package com.core.domain.repository

import com.core.commons.Resource
import com.core.domain.User
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface UserRepository : Repository {

    suspend fun retrieve(username: String): Resource<User>
}