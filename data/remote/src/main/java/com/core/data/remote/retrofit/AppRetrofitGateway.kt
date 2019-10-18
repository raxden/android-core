package com.core.data.remote.retrofit

import com.core.common.android.Result
import com.core.data.remote.entity.ProjectEntity
import com.core.data.remote.entity.UserEntity
import com.core.data.remote.AppGateway
import com.core.data.remote.retrofit.service.AppRetrofitService
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class AppRetrofitGateway(
    private val service: AppRetrofitService
) : AppGateway {

    override suspend fun user(username: String): Result<UserEntity> {
        return try {
            val response = service.retrieveUser(username)
            getResult(response,
                IOException("Error getting user ${response.code()} ${response.message()}")
            )
        } catch (e: Exception) {
            Result.Error(IOException("Error getting user", e))
        }
    }

    override suspend fun projectList(username: String): Result<List<ProjectEntity>> {
        return try {
            val response = service.retrieveProjectList(username)
            getResult(response,
                IOException("Error getting projects ${response.code()} ${response.message()}")
            )
        } catch (e: Exception) {
            Result.Error(IOException("Error getting projects", e))
        }
    }

    override suspend fun project(username: String, projectName: String): Result<ProjectEntity> {
        return try {
            val response = service.retrieveProjectDetail(username, projectName)
            getResult(response,
                IOException("Error getting project ${response.code()} ${response.message()}")
            )
        } catch (e: Exception) {
            Result.Error(IOException("Error getting project", e))
        }
    }

    private fun <T : Any> getResult(
        response: Response<T>,
        exception: Exception
    ): Result<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }
        return Result.Error(exception)
    }
}
