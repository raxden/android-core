package com.core.data.network.gateway

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Maybe
import io.reactivex.Single

interface AppGateway {

    fun user(
            username: String
    ): Maybe<UserEntity>

    fun projectList(
            username: String
    ): Maybe<List<ProjectEntity>>

    fun project(
            username: String,
            projectName: String
    ): Maybe<ProjectEntity>
}
