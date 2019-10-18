package com.core.domain.interactor.impl

import com.core.commons.Resource
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

    override suspend fun execute(): Resource<List<Project>> {
        return accountRepository.retrieve().data?.let {
            projectRepository.list(it.username)
        } ?: Resource.success(emptyList())
    }

    override suspend fun execute(username: String): Resource<List<Project>> {
        return projectRepository.list(username)
    }
}
