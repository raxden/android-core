package com.core.data.network.gateway.stream

import android.content.Context
import com.core.commons.extension.fromJson
import com.core.data.network.gateway.AppGateway
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raxdenstudios.commons.util.AssetsUtils
import io.reactivex.Maybe
import io.reactivex.Single

class AppFileStreamGateway(
        private val context: Context,
        private val gson: Gson
) : AppGateway {

    override fun user(username: String): Maybe<UserEntity> = Maybe.just(
            gson.fromJson(AssetsUtils.getString(context, "user.json"))
    )

    override fun projectList(username: String): Maybe<List<ProjectEntity>> = Maybe.just(
            gson.fromJson(AssetsUtils.getString(context, "repos.json"))
    )

    override fun project(username: String, projectName: String): Maybe<ProjectEntity> = Maybe.just(
            gson.fromJson(AssetsUtils.getString(context, "repo.json"))
    )
}
