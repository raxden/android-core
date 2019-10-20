package com.core.data.repository

import androidx.lifecycle.MutableLiveData
import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.data.local.dao.ProjectDao
import com.core.data.remote.AppGateway
import com.core.data.remote.entity.ProjectEntity
import com.core.data.repository.mapper.ProjectDataMapper
import com.core.data.repository.util.NetworkBoundResource
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val dao: ProjectDao,
    private val mapper: ProjectDataMapper,
    @Named("io") private val dispatcher: CoroutineDispatcher
) : ProjectRepository {

    override suspend fun list(username: String) = withContext(dispatcher) {
        object: NetworkBoundResource<List<Project>, List<ProjectEntity>>() {

            override suspend fun saveCallResult(data: List<ProjectEntity>) {
                dao.insert(*mapper.transform(data).toTypedArray())
            }

            override fun shouldFetch(data: List<Project>?) = true

            override suspend fun loadFromDb() = dao.findAll()

            override suspend fun createCall(): Result<List<ProjectEntity>> {
                return gateway.projectList(username)
            }

        }.build().asLiveData()
    }

    override suspend fun detail(username: String, projectName: String) = withContext(dispatcher) {
        when (val result = gateway.project(username, projectName)) {
            is Result.Success -> Resource.success(mapper.transform(result.data))
            is Result.Error -> Resource.error(result.exception)
        }
        MutableLiveData<Resource<Project>>()
    }
}