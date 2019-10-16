package com.core.domain.interactor

import com.core.commons.Resource

interface GetVersionUseCase {

    suspend fun execute(): Resource<String>
}