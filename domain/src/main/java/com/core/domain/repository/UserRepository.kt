package com.core.domain.repository

import com.core.common.android.Resource
import com.core.domain.User

interface UserRepository {

    suspend fun retrieve(username: String): Resource<User>
}