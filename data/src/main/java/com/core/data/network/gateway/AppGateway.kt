package com.core.data.network.gateway

import com.core.data.network.entity.ProjectEntity
import io.reactivex.Maybe
import io.reactivex.Single

interface AppGateway {

    fun projectList(
            user: String
    ): Maybe<List<ProjectEntity>>

    fun projectDetail(
        user: String,
        projectName: String
    ): Single<ProjectEntity>

}
