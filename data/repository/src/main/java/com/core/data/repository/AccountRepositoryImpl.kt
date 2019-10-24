package com.core.data.repository

import androidx.lifecycle.liveData
import com.core.common.android.Resource
import com.core.data.local.dao.AccountDao
import com.core.domain.Account
import com.core.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject internal constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    override suspend fun retrieve() = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(accountDao.findAll().first()))
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }

    override suspend fun retrieve(username: String) = liveData {
        emit(Resource.loading(null))
        try {
            emit(Resource.success(accountDao.find(username)))
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }

    override suspend fun save(account: Account) = liveData {
        emit(Resource.loading(null))
        try {
            accountDao.insert(account)
            emit(Resource.success(true))
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }

    override suspend fun remove(account: Account) = liveData {
        emit(Resource.loading(null))
        try {
            accountDao.delete(account)
            emit(Resource.success(true))
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }
}