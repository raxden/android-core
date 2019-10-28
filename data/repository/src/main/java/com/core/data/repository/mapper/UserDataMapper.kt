package com.core.data.repository.mapper

import com.core.common.android.DataMapper
import com.core.common.android.extensions.toLocalDateTime
import com.core.data.remote.entity.UserEntity
import com.core.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataMapper @Inject constructor(): DataMapper<UserEntity, User>() {

    override fun transform(source: UserEntity) = User(
        id = source.id ?: 0L,
        username = source.login ?: "",
        type = source.type ?: "",
        name = source.name ?: "",
        company = source.company ?: "",
        blog = source.blog ?: "",
        location = source.location ?: "",
        email = source.email ?: "",
        followers = source.followers ?: "",
        following = source.following ?: "",
        createdAt = source.created_at?.toLocalDateTime(""),
        updatedAt = source.updated_at?.toLocalDateTime("")
    )

    override fun inverse(source: User): UserEntity = UserEntity(
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    )
}