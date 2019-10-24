package com.core.domain.repository

import androidx.lifecycle.LiveData
import com.core.common.android.Resource
import com.core.domain.Account

interface AccountRepository {

    suspend fun retrieve(): LiveData<Resource<Account>>

    suspend fun retrieve(username: String): LiveData<Resource<Account>>

    suspend fun save(account: Account): LiveData<Resource<Boolean>>

    suspend fun remove(account: Account): LiveData<Resource<Boolean>>
}