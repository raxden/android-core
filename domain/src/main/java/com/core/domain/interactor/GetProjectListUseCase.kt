package com.core.domain.interactor

import com.core.domain.Project
import io.reactivex.Maybe

interface GetProjectListUseCase {

    fun execute(): Maybe<List<Project>>

    fun execute(username: String): Maybe<List<Project>>
}
