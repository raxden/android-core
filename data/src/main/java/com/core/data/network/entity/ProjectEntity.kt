package com.core.data.network.entity

import android.net.Uri
import android.text.TextUtils
import com.core.domain.Project
import com.core.domain.User
import com.google.gson.annotations.Expose

data class ProjectEntity(
        @Expose val id: Long? = null,
        @Expose val name: String? = null,
        @Expose val description: String? = null,
        @Expose val user: UserEntity? = null,
        @Expose val uri: String? = null,
        @Expose val createdAt: String? = null,
        @Expose val updatedAt: String? = null
) {

    fun toProject(): Project = Project(
            id = id ?: 0L,
            name = name ?: "",
            description = description ?: "",
            user = user?.toUser(),
            uri = Uri.parse(uri),
            createdAt = null,
            updatedAt = null
    )
}
