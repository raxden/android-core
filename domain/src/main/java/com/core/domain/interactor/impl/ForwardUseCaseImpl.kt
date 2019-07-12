package com.core.domain.interactor.impl

import com.core.domain.Forward
import com.core.domain.User
import com.core.domain.interactor.ForwardUseCase
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class ForwardUseCaseImpl @Inject constructor(
        private val accountRepository: AccountRepository,
        private val userRepository: UserRepository
) : ForwardUseCase {

    override fun execute(): Single<Pair<Forward, User?>> = accountRepository
            .retrieve().map { true }
            .onErrorResumeNext { Single.just(false) }
            .map { hasAccount ->
                if (hasAccount) Forward.HOME
                else Forward.LOGIN
            }
            .flatMap {
                when (it) {
                    Forward.LOGIN -> Single.just(Pair(it, null))
                    Forward.HOME -> accountRepository.retrieve()
                            .flatMap { account -> userRepository.retrieve(account.username) }
                            .map { user -> Pair(it, user) }
                }
            }
}
