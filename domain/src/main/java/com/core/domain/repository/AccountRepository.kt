package com.core.domain.repository

import com.core.common.android.Resource
import com.core.domain.Account

interface AccountRepository {

    suspend fun retrieve(): Resource<Account>

    suspend fun retrieve(id: Long): Resource<Account>

    suspend fun save(account: Account): Resource<Account>

    suspend fun remove(account: Account)
}