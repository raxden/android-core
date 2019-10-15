package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.User

interface LoginCOUseCase {

    suspend fun execute(username: String): Resource<User>
}
