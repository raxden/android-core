package com.core.data.network.gateway

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Maybe
import io.reactivex.Single

interface AppGateway {

    fun user(
            user: String
    ): Maybe<UserEntity>

    fun projectList(
            user: String
    ): Maybe<List<ProjectEntity>>

    fun projectDetail(
        user: String,
        projectName: String
    ): Maybe<ProjectEntity>
}
