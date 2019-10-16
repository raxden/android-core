package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Forward
import com.core.domain.User
import io.reactivex.Single

interface ForwardUseCase {

    suspend fun execute(): Resource<Pair<Forward, User?>>
}