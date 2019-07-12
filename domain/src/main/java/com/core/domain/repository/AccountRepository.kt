package com.core.domain.repository

import com.core.domain.Account
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface AccountRepository : Repository {

    fun retrieve(): Single<Account>

    fun retrieve(id: Long): Single<Account>

    fun save(account: Account) : Single<Account>

    fun remove(account: Account): Completable
}