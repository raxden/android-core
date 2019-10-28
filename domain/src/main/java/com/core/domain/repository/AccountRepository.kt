package com.core.domain.repository

import com.core.domain.Account
import io.reactivex.Single

interface AccountRepository {

    fun retrieve(): Single<Result<Account>>

    fun retrieve(username: String): Single<Result<Account>>

    fun save(account: Account): Single<Result<Boolean>>

    fun remove(account: Account): Single<Result<Boolean>>
}