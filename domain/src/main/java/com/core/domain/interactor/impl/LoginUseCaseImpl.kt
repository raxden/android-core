package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.repository.ProjectRepository
import io.reactivex.Single
import javax.inject.Inject

class GetProjectDetailUseCaseImpl @Inject constructor(
    projectRepository: ProjectRepository
) : BaseUseCaseImpl<ProjectRepository>(projectRepository),
    GetProjectDetailUseCase {

    override fun execute(userId: String, projectName: String): Single<Project> {
        return repository.detail(userId, projectName)
    }
}
