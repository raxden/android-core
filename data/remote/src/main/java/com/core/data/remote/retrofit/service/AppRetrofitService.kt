package com.core.data.remote.retrofit.service

import com.core.data.remote.entity.ProjectEntity
import com.core.data.remote.entity.UserEntity
import retrofit2.Response
import retrofit2.http.*

interface AppRetrofitService {

    @GET("users/{user}")
    suspend fun retrieveUser(
            @Path("user") username: String
    ): Response<UserEntity>

    @GET("users/{user}/repos")
    suspend fun retrieveProjectList(
        @Path("user") username: String
    ): Response<List<ProjectEntity>>

    @GET("repos/{user}/{reponame}")
    suspend fun retrieveProjectDetail(
        @Path("user") username: String,
        @Path("reponame") projectName: String
    ): Response<ProjectEntity>
}