package com.core.domain.repository

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface ProjectRepository : Repository {

    fun list(username: String): Flowable<Resource<List<Project>>>

    fun detail(username: String, projectName: String): Single<Project>
}