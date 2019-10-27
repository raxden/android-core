package com.core.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.data.remote.AppGateway
import com.core.data.repository.mapper.ProjectDataMapper
import com.core.domain.Project
import com.core.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject internal constructor(
    private val gateway: AppGateway,
    private val mapper: ProjectDataMapper
) : ProjectRepository {

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