package com.core.domain.interactor

import com.core.common.android.Resource
import com.core.domain.Account
import com.core.domain.User
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
        private val userRepository: UserRepository,
        private val accountRepository: AccountRepository
) {

    suspend fun execute(username: String): Resource<User> {
        val user = userRepository.retrieve(username).data
        return if (user != null) {
            accountRepository.save(Account(username = user.username))
            Resource.success(user)
        } else {
            Resource.error(Throwable("User not found"), null)
        }
    }
}
