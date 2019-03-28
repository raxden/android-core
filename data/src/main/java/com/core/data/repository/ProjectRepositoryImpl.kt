package com.core.data.repository

import com.core.data.entity.mapper.ProjectEntityDataMapper
import com.core.data.network.gateway.AppGateway
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val entityDataMapper: ProjectEntityDataMapper
) : ProjectRepository {

    override fun list(userId: String): Maybe<List<Project>> = gateway
            .projectList(userId)
            .map { entityDataMapper.transform(it) }

    override fun detail(userId: String, projectName: String): Single<Project> = gateway
            .projectDetail(userId, projectName)
            .map { entityDataMapper.transform(it) }
}