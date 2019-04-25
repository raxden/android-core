package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetProjectListUseCaseImpl @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val accountRepository: AccountRepository
) : GetProjectListUseCase {

    override fun execute(): Maybe<List<Project>> = accountRepository.retrieve()
            .flatMap { projectRepository.list(it.username) }

    override fun execute(username: String): Maybe<List<Project>> = projectRepository.list(username)
}
