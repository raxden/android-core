package com.core.data.repository

import com.core.data.persistence.AppDatabase
import com.core.domain.Account
import com.core.domain.repository.AccountRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class AccountRepositoryImpl @Inject internal constructor(
        private val appDatabase: AppDatabase
) : AccountRepository {

    override fun retrieve(): Single<Account> = appDatabase.accountDao().findAll().map { it.first() }.toSingle()

    override fun retrieve(id: Long): Single<Account> = appDatabase.accountDao().find(id)

    override fun save(account: Account): Single<Account> {
        return if (account.id == 0L) {
            appDatabase.accountDao().insert(account).flatMap {
                appDatabase.accountDao().find(it)
            }
        } else appDatabase.accountDao().update(account).andThen(Single.just(account))
    }

    override fun remove(account: Account): Completable = appDatabase.accountDao().delete(account)
}