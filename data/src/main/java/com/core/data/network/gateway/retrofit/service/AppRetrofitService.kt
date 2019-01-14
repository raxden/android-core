package com.core.data.network.gateway.retrofit.service

import com.core.data.entity.ProjectEntity
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.*

interface AppRetrofitService {

    @GET("users/{user}/repos")
    fun retrieveProjectList(
        @Path("user") user: String
    ): Maybe<List<ProjectEntity>>

    @GET("users/{user}/repos")
    fun retrieveProjectDetail(
        @Path("user") user: String,
        @Path("reponame") projectName: String
    ): Single<ProjectEntity>

}
