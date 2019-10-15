package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.Account
import com.core.domain.User
import com.core.domain.interactor.LoginCOUseCase
import com.core.domain.interactor.LoginUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class LoginCOUseCaseImpl @Inject constructor(
        private val userRepository: UserRepository,
        private val accountRepository: AccountRepository
) : LoginCOUseCase {

    override suspend fun execute(username: String): Resource<User> {
        return userRepository.retrieveCO(username)
    }
}
