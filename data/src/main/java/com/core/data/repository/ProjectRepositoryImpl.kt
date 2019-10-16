package com.core.data.repository

import com.core.commons.Resource
import com.core.data.network.gateway.AppGateway
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProjectRepositoryImpl @Inject internal constructor(
        private val gateway: AppGateway,
        @Named("io") private val dispatcher: CoroutineDispatcher
) : ProjectRepository {

    override suspend fun list(username: String) = withContext(dispatcher) {
        try {
            Resource.success(gateway.projectList(username).map { entity -> entity.toProject() })
        } catch (e: Exception) {
            Resource.error(e, null)
        }
    }

    override suspend fun detail(username: String, projectName: String) = withContext(dispatcher) {
        try {
            Resource.success(gateway.project(username, projectName).toProject())
        } catch (e: Exception) {
            Resource.error(e, null)
        }
    }
}