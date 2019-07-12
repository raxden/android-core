package com.core.domain.interactor

import com.core.domain.Forward
import com.core.domain.User
import io.reactivex.Single

interface ForwardUseCase {

    fun execute(): Single<Pair<Forward, User?>>
}