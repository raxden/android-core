package com.core.data.repository

import androidx.lifecycle.LiveData
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val dao: ProjectDao,
    private val mapper: ProjectDataMapper
) : ProjectRepository {

    override suspend fun list(username: String) : LiveData<Resource<List<Project>>> {
        return object : NetworkBoundResource<List<Project>, List<ProjectEntity>>() {

            override suspend fun saveCallResult(data: List<ProjectEntity>) {
                val dataToInsert = withContext(Dispatchers.IO) {
                    mapper.transform(data).toTypedArray()
                }
                dao.insert(*dataToInsert)
            }

            override fun shouldFetch(data: List<Project>?) = true

            override suspend fun loadFromDb() = dao.findAll(username)

            override suspend fun createCall() = gateway.projectList(username)

        }.build().asLiveData()
    }

    override suspend fun detail(username: String, projectName: String): LiveData<Resource<Project>> {
        return object : NetworkBoundResource<Project, ProjectEntity>() {

            override suspend fun saveCallResult(data: ProjectEntity) {
                val dataToInsert = withContext(Dispatchers.IO) {
                    mapper.transform(data)
                }
                dao.insert(dataToInsert)
            }

            override fun shouldFetch(data: Project?) = true

            override suspend fun loadFromDb() = dao.find(username, projectName)

            override suspend fun createCall() = gateway.project(username, projectName)

        }.build().asLiveData()
    }
}