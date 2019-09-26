package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class GetProjectListUseCaseImpl @Inject constructor(
        private val projectRepository: ProjectRepository,
        private val accountRepository: AccountRepository
) : GetProjectListUseCase {

    override fun execute(): Maybe<List<Project>> = accountRepository
            .retrieve()
            .flatMapMaybe {
                projectRepository.list(it.username)
            }

    override fun execute(username: String): Maybe<List<Project>> = projectRepository
            .list(username)

    override fun test(username: String): Flowable<List<Project>> = projectRepository
            .test(username)

    override fun test2(username: String): Flowable<Resource<List<Project>>> = projectRepository
            .observeList(username)

}
