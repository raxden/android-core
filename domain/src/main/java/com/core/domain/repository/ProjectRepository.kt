package com.core.domain.repository

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Maybe
import io.reactivex.Single

interface ProjectRepository : Repository {

    suspend fun list(username: String): Resource<List<Project>>

    suspend fun detail(username: String, projectName: String): Resource<Project>
}