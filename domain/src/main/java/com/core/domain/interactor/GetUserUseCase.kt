package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Forward
import com.core.domain.User
import io.reactivex.Single

interface GetUserUseCase {

    suspend fun execute(): Resource<User>
}