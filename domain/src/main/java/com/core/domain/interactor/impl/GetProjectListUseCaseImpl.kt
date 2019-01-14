package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetProjectListUseCaseImpl @Inject constructor(
    projectRepository: ProjectRepository
) : BaseUseCaseImpl<ProjectRepository>(projectRepository),
    GetProjectListUseCase {

    override fun execute(userId: String): Maybe<List<Project>> {
        return repository.list(userId)
    }
}
