package com.core.data.remote.stream

import com.core.data.remote.entity.ProjectEntity
import com.core.data.remote.entity.UserEntity
import com.core.data.remote.AppGateway
import retrofit2.Response

class AppFileStreamGateway(
//        private val context: Context,
//        private val gson: Gson
) : AppGateway {

    companion object {
        const val IN_MILLISECONDS: Long = 2000
    }

    /*
    override fun user(username: String): Single<UserEntity> = Single
            .timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)
            .map { gson.fromJson<UserEntity>(AssetsUtils.getString(context, "user.json") ?: "") }
*/
    override suspend fun user(username: String): Response<UserEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun projectList(username: String): Response<List<ProjectEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun project(username: String, projectName: String): Response<ProjectEntity> {
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
