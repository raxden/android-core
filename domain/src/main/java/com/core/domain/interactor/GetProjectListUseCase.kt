package com.core.domain.interactor

import com.core.common.android.Resource
import com.core.domain.Project
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import javax.inject.Inject

class GetProjectListUseCase @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val accountRepository: AccountRepository
) {

    suspend fun execute(): Resource<List<Project>> {
        val account  = accountRepository.retrieve().data
        return if (account != null) {
            projectRepository.list(account.username)
        } else {
            Resource.success(emptyList())
        }
    }

    suspend fun execute(username: String): Resource<List<Project>> {
        return projectRepository.list(username)
    }
}
