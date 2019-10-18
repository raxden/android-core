package com.core.domain.interactor

import com.core.common.android.Resource
import com.core.domain.User
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
        private val accountRepository: AccountRepository,
        private val userRepository: UserRepository
) {

    suspend fun execute(): Resource<User> {
        val account = accountRepository.retrieve().data
        return if (account != null) {
            userRepository.retrieve(account.username)
        } else {
            Resource.error(Throwable("User not found"), null)
        }
    }
}
