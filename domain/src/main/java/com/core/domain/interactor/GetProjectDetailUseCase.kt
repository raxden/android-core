package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Maybe
import io.reactivex.Single

interface GetProjectDetailUseCase {

    suspend fun execute(projectName: String): Resource<Project>

    suspend fun execute(username: String, projectName: String): Resource<Project>
}
