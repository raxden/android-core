package com.core.data.network.gateway.retrofit.service

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.*

interface AppRetrofitService {

    @GET("users/{user}")
    fun retrieveUser(
            @Path("user") username: String
    ): Single<UserEntity>


    @GET("users/{user}/repos")
    fun retrieveProjectList(
        @Path("user") username: String
    ): Single<List<ProjectEntity>>

    @GET("repos/{user}/{reponame}")
    fun retrieveProjectDetail(
        @Path("user") username: String,
        @Path("reponame") projectName: String
    ): Single<ProjectEntity>
}
