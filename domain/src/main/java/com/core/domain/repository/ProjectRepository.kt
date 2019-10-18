package com.core.domain.repository

import com.core.common.android.Resource
import com.core.domain.Project

interface ProjectRepository {

    suspend fun list(username: String): Resource<List<Project>>

    suspend fun detail(username: String, projectName: String): Resource<Project>
}