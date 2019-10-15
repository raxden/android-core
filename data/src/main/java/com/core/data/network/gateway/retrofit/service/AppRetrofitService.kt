package com.core.data.network.gateway.retrofit.service

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface AppRetrofitService {

    @GET("users/{user}")
    fun retrieveUser(
            @Path("user") username: String
    ): Single<UserEntity>

    @GET("users/{user}")
    suspend fun retrieveUserCO(
            @Path("user") username: String
    ): Response<UserEntity>

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
