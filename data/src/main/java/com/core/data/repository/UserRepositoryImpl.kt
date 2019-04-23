package com.core.data.repository

import com.core.data.persistence.AppDatabase
import com.core.domain.User
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject internal constructor(
        private val appDatabase: AppDatabase
) : UserRepository {

    override fun retrieve(id: Long): Single<User> = appDatabase.userDao().find(id.toString())

    override fun save(user: User): Single<User> {
        return if (user.id == 0L) appDatabase.userDao().insert(user)
        else appDatabase.userDao().update(user).andThen(Single.just(user))
    }
}