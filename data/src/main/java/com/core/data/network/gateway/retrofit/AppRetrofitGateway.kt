package com.core.data.network.gateway.retrofit

import com.core.data.network.gateway.AppGateway
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import io.reactivex.Maybe
import io.reactivex.Single

class AppRetrofitGateway(
        private val service: AppRetrofitService
) : AppGateway {

    override fun user(username: String): Single<UserEntity> = service
            .retrieveUser(username)

    override fun projectList(username: String): Single<List<ProjectEntity>> = service
            .retrieveProjectList(username)

    override fun project(username: String, projectName: String): Single<ProjectEntity> = service
            .retrieveProjectDetail(username, projectName)
}
