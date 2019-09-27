package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetProjectListUseCaseImpl @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val accountRepository: AccountRepository
) : GetProjectListUseCase {

    override fun execute(): Flowable<Resource<List<Project>>> = accountRepository
            .retrieve()
            .toFlowable()
            .flatMap { projectRepository.list(it.username) }

    override fun execute(username: String): Flowable<Resource<List<Project>>> = projectRepository
            .list(username)
}
