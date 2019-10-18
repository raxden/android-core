package com.core.domain.interactor

import com.core.common.android.Resource
import com.core.domain.Project
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import javax.inject.Inject

class GetProjectDetailUseCase @Inject constructor(
    private val projectRepository: ProjectRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun execute(projectName: String): Resource<Project> {
        val account = accountRepository.retrieve().data
        return if (account != null) {
            projectRepository.detail(account.username, projectName)
        } else {
            Resource.error(Throwable("Project doesn't exist"), null)
        }
    }

    suspend fun execute(username: String, projectName: String): Resource<Project> {
        return projectRepository.detail(username, projectName)
    }
}
