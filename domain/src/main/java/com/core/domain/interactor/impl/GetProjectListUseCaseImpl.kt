package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetProjectListUseCaseImpl @Inject constructor(
        projectRepository: ProjectRepository,
        private val userRepository: UserRepository
) : BaseUseCaseImpl<ProjectRepository>(projectRepository),
        GetProjectListUseCase {

    override fun execute(): Maybe<List<Project>> = userRepository.retrieve()
            .flatMapMaybe { repository.list(it.username) }

    override fun execute(userId: String): Maybe<List<Project>> = repository.list(userId)
}
