package com.core.data.repository

import com.core.data.persistence.AppDatabase
import com.core.domain.Account
import com.core.domain.repository.AccountRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class AccountRepositoryImpl @Inject internal constructor(
        private val appDatabase: AppDatabase
) : AccountRepository {

    override fun retrieve(): Maybe<Account> = appDatabase.accountDao().findAll().map { it.first() }

    override fun retrieve(id: Long): Maybe<Account> = appDatabase.accountDao().find(id)

    override fun save(account: Account): Single<Account> {
        return if (account.id == 0L) {
            appDatabase.accountDao().insert(account).flatMap {
                appDatabase.accountDao().find(it).toSingle()
            }
        } else appDatabase.accountDao().update(account).andThen(Single.just(account))
    }
}