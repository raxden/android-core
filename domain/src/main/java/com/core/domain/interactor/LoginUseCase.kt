package com.core.domain.interactor

import com.core.domain.Account
import com.core.domain.User
import io.reactivex.Maybe
import io.reactivex.Single

interface LoginUseCase {

    fun execute(username: String): Single<Account>
}
