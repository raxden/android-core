package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.Account
import com.core.domain.User
import com.core.domain.interactor.LoginUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
        private val userRepository: UserRepository,
        private val accountRepository: AccountRepository
) : LoginUseCase {

    override suspend fun execute(username: String): Resource<User> {
        return userRepository.retrieve(username).also { resource ->
            resource.data?.let { user ->
                accountRepository.save(Account(username = user.username))
            }
        }
    }
}
