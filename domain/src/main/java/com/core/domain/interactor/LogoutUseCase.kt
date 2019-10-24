package com.core.domain.interactor

import com.core.common.android.Resource
import com.core.domain.repository.AccountRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LogoutUseCase @Inject constructor(
        private val accountRepository: AccountRepository
) {

    suspend fun execute(): Resource<Boolean> {
//        val account = accountRepository.retrieve().data
//        return if (account != null) {
//            accountRepository.remove(account)
//            Resource.success(true)
//        } else {
//            Resource.error(Throwable("User not found"), null)
//        }
        return Resource.success(true)
    }
}
