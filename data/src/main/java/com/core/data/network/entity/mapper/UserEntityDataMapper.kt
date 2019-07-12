package com.core.data.network.entity.mapper

import android.content.Context
import com.core.commons.DataMapper
import com.core.commons.extension.mapTo
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.domain.Project
import com.core.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserEntityDataMapper @Inject internal constructor(context: Context) : DataMapper<UserEntity, User>(context) {

    override fun transform(source: UserEntity): User = source.mapTo<User>().also {
        it.username = source.login ?: ""
    }

    override fun inverse(source: User): UserEntity = source.mapTo()
}
