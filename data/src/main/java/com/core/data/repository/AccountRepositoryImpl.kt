package com.core.data.repository

import com.core.commons.Resource
import com.core.data.persistence.AppDatabase
import com.core.domain.Account
import com.core.domain.repository.AccountRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class AccountRepositoryImpl @Inject internal constructor(
        private val appDatabase: AppDatabase,
        @Named("io") private val dispatcher: CoroutineDispatcher
) : AccountRepository {

    override suspend fun retrieve() = withContext(dispatcher) {
        Resource.success(appDatabase.accountDao().findAll().first())
    }

    override suspend fun retrieve(id: Long) = withContext(dispatcher) {
        Resource.success(appDatabase.accountDao().find(id))
    }

    override suspend fun save(account: Account) = withContext(dispatcher) {
        if (account.id == 0L) {
            appDatabase.accountDao().insert(account).let { id ->
                Resource.success(appDatabase.accountDao().find(id))
            }
        } else {
            appDatabase.accountDao().update(account)
            Resource.success(account)
        }
    }

    override suspend fun remove(account: Account) = withContext(dispatcher) {
        appDatabase.accountDao().delete(account)
    }
}