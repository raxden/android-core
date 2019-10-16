package com.core.domain.interactor.impl

import com.core.commons.Resource
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

    override suspend fun execute(): Resource<Pair<Forward, User?>> {
        return accountRepository.retrieve().data?.let {account ->
            Resource.success(Pair(Forward.HOME, userRepository.retrieve(account.username).data))
        } ?: Resource.success(Pair(Forward.LOGIN, null))
    }
}
