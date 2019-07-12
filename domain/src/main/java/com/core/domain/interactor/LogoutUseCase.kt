package com.core.domain.interactor

import io.reactivex.Completable

interface LogoutUseCase {

    fun execute(): Completable
}
