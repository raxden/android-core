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

    override fun execute(): Single<Pair<Forward, User?>> = Single.just(Pair(Forward.LOGIN, null))
}
