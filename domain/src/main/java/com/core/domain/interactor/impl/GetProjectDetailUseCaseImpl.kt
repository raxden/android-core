package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetProjectDetailUseCaseImpl @Inject constructor(
        private val projectRepository: ProjectRepository
) : GetProjectDetailUseCase {

    override fun execute(username: String, projectName: String): Maybe<Project> = projectRepository
            .detail(username, projectName)
}
