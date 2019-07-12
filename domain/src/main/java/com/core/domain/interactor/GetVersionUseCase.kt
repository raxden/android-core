package com.core.domain.interactor

import io.reactivex.Single

interface GetVersionUseCase {

    fun execute(): Single<String>
}