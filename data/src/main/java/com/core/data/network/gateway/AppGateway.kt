package com.core.data.network.gateway

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Single
import retrofit2.Response

interface AppGateway {

    fun user(
            username: String
    ): Single<UserEntity>

    suspend fun userCO(
            username: String
    ): Response<UserEntity>

    fun projectList(
            username: String
    ): Single<List<ProjectEntity>>

    fun project(
            username: String,
            projectName: String
    ): Single<ProjectEntity>
}
