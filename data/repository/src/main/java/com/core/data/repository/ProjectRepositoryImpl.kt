package com.core.data.repository

import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.data.remote.AppGateway
import com.core.data.repository.mapper.ProjectDataMapper
import com.core.domain.repository.ProjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val mapper: ProjectDataMapper,
    @Named("io") private val dispatcher: CoroutineDispatcher
) : ProjectRepository {

    override suspend fun list(username: String) = withContext(dispatcher) {
        when (val result = gateway.projectList(username)) {
            is Result.Success -> Resource.success(mapper.transform(result.data))
            is Result.Error -> Resource.error(result.exception)
        }
    }

    override suspend fun detail(username: String, projectName: String) = withContext(dispatcher) {
        when (val result = gateway.project(username, projectName)) {
            is Result.Success -> Resource.success(mapper.transform(result.data))
            is Result.Error -> Resource.error(result.exception)
        }
    }
}