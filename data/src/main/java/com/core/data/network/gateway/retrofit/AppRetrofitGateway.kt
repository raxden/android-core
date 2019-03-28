package com.core.data.network.gateway.retrofit

import com.core.data.network.gateway.AppGateway
import com.core.data.entity.ProjectEntity
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import io.reactivex.Maybe
import io.reactivex.Single

class AppRetrofitGateway(
        private val service: AppRetrofitService
) : AppGateway {

    override fun projectList(user: String): Maybe<List<ProjectEntity>> = service
            .retrieveProjectList(user)

    override fun projectDetail(user: String, projectName: String): Single<ProjectEntity> = service
            .retrieveProjectDetail(user, projectName)
}
