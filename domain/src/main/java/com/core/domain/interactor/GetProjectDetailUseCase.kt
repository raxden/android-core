package com.core.domain.interactor

import com.core.domain.Project
import io.reactivex.Single

interface GetProjectDetailUseCase {

    fun execute(userId: String, projectName: String): Single<Project>

}
