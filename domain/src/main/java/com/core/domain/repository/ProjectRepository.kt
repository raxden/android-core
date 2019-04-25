package com.core.domain.repository

import com.core.domain.Project
import io.reactivex.Maybe
import io.reactivex.Single

interface ProjectRepository : Repository {

    fun list(username: String): Maybe<List<Project>>

    fun detail(username: String, projectName: String): Maybe<Project>
}