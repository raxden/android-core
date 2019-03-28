package com.core.domain.interactor

import com.core.domain.User
import io.reactivex.Single

interface LoginUseCase {

    fun execute(username: String, password: String): Single<User>
}
