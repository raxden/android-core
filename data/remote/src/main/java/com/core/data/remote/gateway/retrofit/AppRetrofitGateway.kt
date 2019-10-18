package com.core.data.remote.gateway.retrofit

import com.core.data.model.ProjectEntity
import com.core.data.model.UserEntity
import com.core.data.remote.gateway.AppGateway
import com.core.data.remote.gateway.retrofit.service.AppRetrofitService
import retrofit2.Response

class AppRetrofitGateway(
    private val service: AppRetrofitService
) : AppGateway {

    override suspend fun user(username: String): Response<UserEntity> {
        return service.retrieveUser(username)
    }

    override suspend fun projectList(username: String): Response<List<ProjectEntity>> {
        return service.retrieveProjectList(username)
    }

    override suspend fun project(username: String, projectName: String): Response<ProjectEntity> {
        return service.retrieveProjectDetail(username, projectName)
    }
}
