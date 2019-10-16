package com.core.domain.interactor.impl

import com.core.domain.interactor.LogoutUseCase
import com.core.domain.repository.AccountRepository
import io.reactivex.Completable
import javax.inject.Inject

class LogoutUseCaseImpl @Inject constructor(
        private val accountRepository: AccountRepository
) : LogoutUseCase {

    override suspend fun execute() {
        accountRepository.retrieve().data?.let {
            accountRepository.remove(it)
        }
    }
}
