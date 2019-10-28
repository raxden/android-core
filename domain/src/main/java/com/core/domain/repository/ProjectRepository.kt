package com.core.domain.repository

import com.core.domain.Project
import io.reactivex.Single

interface ProjectRepository {

    fun list(username: String): Single<Result<List<Project>>>

    fun detail(username: String, projectName: String): Single<Result<Project>>
}