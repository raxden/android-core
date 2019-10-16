package com.core.data.network.gateway

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Single
import retrofit2.Response

interface AppGateway {

    suspend fun user(
            username: String
    ): UserEntity

    suspend fun projectList(
            username: String
    ): List<ProjectEntity>

    suspend fun project(
            username: String,
            projectName: String
    ): ProjectEntity
}
