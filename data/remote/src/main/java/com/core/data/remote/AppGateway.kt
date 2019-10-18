package com.core.data.remote

import com.core.common.android.Result
import com.core.data.remote.entity.ProjectEntity
import com.core.data.remote.entity.UserEntity

interface AppGateway {

    suspend fun user(
        username: String
    ): Result<UserEntity>

    suspend fun projectList(
        username: String
    ): Result<List<ProjectEntity>>

    suspend fun project(
        username: String,
        projectName: String
    ): Result<ProjectEntity>
}
