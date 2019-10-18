package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.Project
import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GetProjectDetailUseCaseImpl @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val accountRepository: AccountRepository
) : GetProjectDetailUseCase {

    override suspend fun execute(projectName: String): Resource<Project> {
        return accountRepository.retrieve().data?.let {
            projectRepository.detail(it.username, projectName)
        } ?: Resource.error(Throwable("Project doesn't exist"), null)
    }

    override suspend fun execute(username: String, projectName: String): Resource<Project> {
        return projectRepository.detail(username, projectName)
    }
}
