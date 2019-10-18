package com.core.data.remote.entity

import com.google.gson.annotations.Expose

data class ProjectEntity(
        @Expose val id: Long? = null,
        @Expose val name: String? = null,
        @Expose val description: String? = null,
        @Expose val user: UserEntity? = null,
        @Expose val url: String? = null,
        @Expose val created_at: String? = null,
        @Expose val updated_at: String? = null
) {

//    fun toProject(): Project = Project(
//            id = id ?: 0L,
//            name = name ?: "",
//            description = description ?: "",
//            user = user?.toUser(),
//            uri = Uri.parse(url),
//            createdAt = null,
//            updatedAt = null
//    )
}
