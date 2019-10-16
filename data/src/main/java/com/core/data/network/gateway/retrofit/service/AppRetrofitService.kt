package com.core.data.network.gateway.retrofit.service

import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface AppRetrofitService {

    @GET("users/{user}")
    suspend fun retrieveUser(
            @Path("user") username: String
    ): UserEntity

    @GET("users/{user}/repos")
    suspend fun retrieveProjectList(
        @Path("user") username: String
    ): List<ProjectEntity>

    @GET("repos/{user}/{reponame}")
    suspend fun retrieveProjectDetail(
        @Path("user") username: String,
        @Path("reponame") projectName: String
    ): ProjectEntity
}
