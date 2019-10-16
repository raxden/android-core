package com.core.data.network.gateway.retrofit

import com.core.data.network.gateway.AppGateway
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import io.reactivex.Single
import retrofit2.Response

class AppRetrofitGateway(
        private val service: AppRetrofitService
) : AppGateway {

    override suspend fun user(username: String): UserEntity = service
            .retrieveUser(username)

    override suspend fun projectList(username: String) = service
            .retrieveProjectList(username)

    override suspend fun project(username: String, projectName: String) = service
            .retrieveProjectDetail(username, projectName)
}
