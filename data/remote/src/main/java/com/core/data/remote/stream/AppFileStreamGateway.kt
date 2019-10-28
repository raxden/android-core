package com.core.data.remote.stream

import com.core.common.android.Result
import com.core.common.android.extensions.fromJson
import com.core.data.remote.entity.ProjectEntity
import com.core.data.remote.entity.UserEntity
import com.core.data.remote.AppGateway
import com.google.gson.Gson
import java.io.File

class AppFileStreamGateway(
    private val gson: Gson
) : AppGateway {

    override suspend fun user(username: String): Result<UserEntity> {
        return Result.Success(gson.fromJson<UserEntity>(getJson("user.json")))
    }

    override suspend fun projectList(username: String): Result<List<ProjectEntity>> {
        return Result.Success(gson.fromJson<List<ProjectEntity>>(getJson("repos.json")))
    }

    override suspend fun project(username: String, projectName: String): Result<ProjectEntity> {
        return Result.Success(gson.fromJson<ProjectEntity>(getJson("repo.json")))
    }

    private fun getJson(path: String): String {
        return this.javaClass.classLoader?.getResource(path)?.let {
            val file = File(it.path)
            return String(file.readBytes())
        } ?: ""
    }
}
