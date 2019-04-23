package com.core.domain.interactor.impl

import com.core.domain.User
import com.core.domain.interactor.LoginUseCase
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
        projectRepository: ProjectRepository,
        userRepository: UserRepository
) : BaseUseCaseImpl<UserRepository>(userRepository),
        LoginUseCase {

    override fun execute(username: String): Single<User> = repository.login(username)
}
