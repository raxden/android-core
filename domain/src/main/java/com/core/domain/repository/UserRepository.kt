package com.core.domain.repository

import androidx.lifecycle.LiveData
import com.core.common.android.Resource
import com.core.domain.Project
import com.core.domain.User

interface UserRepository {

    suspend fun retrieve(username: String): LiveData<Resource<User>>
}