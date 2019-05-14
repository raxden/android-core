package com.core.domain.interactor

import com.core.domain.Project
import io.reactivex.Maybe
import io.reactivex.Single

interface GetProjectDetailUseCase {

    fun execute(projectName: String): Single<Project>

    fun execute(username: String, projectName: String): Single<Project>
}
