package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GetProjectListUseCase {

    fun execute(): Flowable<Resource<List<Project>>>

    fun execute(username: String): Flowable<Resource<List<Project>>>
}
