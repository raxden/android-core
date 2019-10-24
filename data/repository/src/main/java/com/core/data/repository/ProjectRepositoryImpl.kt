package com.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.data.local.dao.ProjectDao
import com.core.data.remote.AppGateway
import com.core.data.repository.mapper.ProjectDataMapper
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val dao: ProjectDao,
    private val mapper: ProjectDataMapper
) : ProjectRepository {

    override suspend fun observeList(
        username: String
    ): LiveData<Resource<List<Project>>> = liveData {
        val disposable = emitSource(
            dao.observeProjects(username).map { Resource.loading(it) }
        )
        try {
            when (val result = gateway.projectList(username)) {
                is Result.Success -> {
                    // Stop the previous emission to avoid dispatching the updated user as `loading`.
                    disposable.dispose()
                    dao.insert(*mapper.transform(result.data).toTypedArray())
                    // Re-establish the emission with success type.
                    emitSource(
                        dao.observeProjects(username).map {
                            Resource.success(it)
                        }
                    )
                }
                is Result.Error -> {
                    emitSource(
                        dao.observeProjects(username).map {
                            Resource.error(result.exception, it)
                        }
                    )
                }
            }
        } catch (e: Exception) {
            emitSource(
                dao.observeProjects(username).map {
                    Resource.error(e, it)
                }
            )
        }
    }

    override suspend fun list(
        username: String
    ): LiveData<Resource<List<Project>>> = liveData {
        try {
            emit(Resource.loading(null))
            when (val result = gateway.projectList(username)) {
                is Result.Success ->
                    emit(Resource.success(mapper.transform(result.data)))
                is Result.Error ->
                    emit(Resource.error(result.exception))
            }
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }

    override suspend fun detail(
        username: String,
        projectName: String
    ): LiveData<Resource<Project>> = liveData {
        try {
            emit(Resource.loading(null))
            when (val result = gateway.project(username, projectName)) {
                is Result.Success ->
                    emit(Resource.success(mapper.transform(result.data)))
                is Result.Error ->
                    emit(Resource.error(result.exception))
            }
        } catch (e: Exception) {
            emit(Resource.error(e))
        }
    }
}