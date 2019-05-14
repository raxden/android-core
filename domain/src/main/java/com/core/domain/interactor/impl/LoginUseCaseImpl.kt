package com.core.domain.interactor.impl

import com.core.domain.Account
import com.core.domain.User
import com.core.domain.interactor.LoginUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
        private val userRepository: UserRepository,
        private val accountRepository: AccountRepository
) : LoginUseCase {

    override fun execute(username: String): Single<Account> = userRepository
            .retrieve(username)
            .flatMap {
                accountRepository.save(Account(username = it.username))
            }
}
