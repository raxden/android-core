package com.core.domain.interactor

import com.core.domain.Project
import io.reactivex.Maybe

interface GetProjectListUseCase {

    fun execute(): Maybe<List<Project>>

    fun execute(userId: String): Maybe<List<Project>>
}
