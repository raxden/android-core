package com.core.domain.interactor

import com.core.commons.Resource
import com.core.domain.Project
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GetProjectListUseCase {

    fun execute(): Maybe<List<Project>>

    fun execute(username: String): Maybe<List<Project>>

    fun test(username: String): Flowable<List<Project>>

    fun test2(username: String): Flowable<Resource<List<Project>>>
}
