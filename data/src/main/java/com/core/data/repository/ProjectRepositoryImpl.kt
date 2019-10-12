package com.core.data.repository

import com.core.data.network.gateway.AppGateway
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class ProjectRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway
) : ProjectRepository {

    override fun list(username: String): Maybe<List<Project>> = gateway
            .projectList(username)
            .map { it.map { entity -> entity.toProject() } }
            .filter { it.isNotEmpty() }

    override fun detail(username: String, projectName: String): Single<Project> = gateway
            .project(username, projectName)
            .map { it.toProject() }
}