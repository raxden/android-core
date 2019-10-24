package com.core.domain.repository

import androidx.lifecycle.LiveData
import com.core.common.android.Resource
import com.core.domain.Project

interface ProjectRepository {

    suspend fun list(username: String): LiveData<Resource<List<Project>>>

    suspend fun observeList(username: String): LiveData<Resource<List<Project>>>

    suspend fun detail(username: String, projectName: String): LiveData<Resource<Project>>
}