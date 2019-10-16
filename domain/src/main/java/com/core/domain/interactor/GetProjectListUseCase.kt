package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Maybe

interface GetProjectListUseCase {

    suspend fun execute(): Resource<List<Project>>

    suspend fun execute(username: String): Resource<List<Project>>
}
