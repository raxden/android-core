package com.core.data.remote.gateway

import com.core.data.model.ProjectEntity
import com.core.data.model.UserEntity
import retrofit2.Response

interface AppGateway {

    suspend fun user(
            username: String
    ): Response<UserEntity>

    suspend fun projectList(
            username: String
    ): Response<List<ProjectEntity>>

    suspend fun project(
            username: String,
            projectName: String
    ): Response<ProjectEntity>
}
