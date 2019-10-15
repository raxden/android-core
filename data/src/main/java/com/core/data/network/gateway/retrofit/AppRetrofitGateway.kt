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

    override fun user(username: String): Single<UserEntity> = service
            .retrieveUser(username)

    override suspend fun userCO(username: String): Response<UserEntity> = service
            .retrieveUserCO(username)

    override fun projectList(username: String): Single<List<ProjectEntity>> = service
            .retrieveProjectList(username)

    override fun project(username: String, projectName: String): Single<ProjectEntity> = service
            .retrieveProjectDetail(username, projectName)
}
