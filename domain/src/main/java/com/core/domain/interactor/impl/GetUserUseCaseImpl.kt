package com.core.domain.interactor.impl

import com.core.commons.Resource
import com.core.domain.User
import com.core.domain.interactor.GetUserUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
        private val accountRepository: AccountRepository,
        private val userRepository: UserRepository
) : GetUserUseCase {

    override suspend fun execute(): Resource<User> {
        return accountRepository.retrieve().data?.let {account ->
            userRepository.retrieve(account.username)
        } ?: Resource.error(Throwable("User not found"), null)
    }
}
