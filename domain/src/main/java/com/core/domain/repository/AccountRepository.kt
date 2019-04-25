package com.core.domain.repository

import com.core.domain.Account
import io.reactivex.Maybe
import io.reactivex.Single

interface AccountRepository : Repository {

    fun retrieve(): Maybe<Account>

    fun retrieve(id: Long): Maybe<Account>

    fun save(account: Account) : Single<Account>
}