package com.core.domain.repository

import com.core.domain.Project
import io.reactivex.Maybe
import io.reactivex.Single

interface ProjectRepository : Repository {

    fun list(userId: String): Maybe<List<Project>>

    fun detail(userId: String, projectName: String): Single<Project>
}