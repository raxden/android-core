package com.core.domain.interactor.impl

import com.core.domain.Project
import com.core.domain.User
import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.interactor.LoginUseCase
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
        userRepository: UserRepository
) : BaseUseCaseImpl<UserRepository>(userRepository),
        LoginUseCase {

    override fun execute(username: String, password: String): Single<User> = repository.login(username, password)
}
