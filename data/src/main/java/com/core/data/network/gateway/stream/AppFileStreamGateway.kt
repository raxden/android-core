package com.core.data.network.gateway.stream

import android.content.Context
import com.core.commons.AssetsUtils
import com.core.commons.extension.fromJson
import com.core.data.network.gateway.AppGateway
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.google.gson.Gson
import io.reactivex.Single
import retrofit2.Response
import java.util.concurrent.TimeUnit

class AppFileStreamGateway(
        private val context: Context,
        private val gson: Gson
) : AppGateway {

    companion object {
        const val IN_MILLISECONDS: Long = 2000
    }

    /*
    override fun user(username: String): Single<UserEntity> = Single
            .timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map { gson.fromJson<UserEntity>(AssetsUtils.getString(context, "user.json") ?: "") }
*/
    override suspend fun user(username: String): UserEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun projectList(username: String): List<ProjectEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun project(username: String, projectName: String): ProjectEntity {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
/*
    override fun projectList(username: String): Single<List<ProjectEntity>> = Single
            .timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map { gson.fromJson<List<ProjectEntity>>(AssetsUtils.getString(context, "repos.json") ?: "") }

    override fun project(username: String, projectName: String): Single<ProjectEntity> = Single
            .timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map { gson.fromJson<ProjectEntity>(AssetsUtils.getString(context, "repo.json") ?: "") }

     */
}
