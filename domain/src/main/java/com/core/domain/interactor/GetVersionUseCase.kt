package com.core.domain.interactor

import android.content.Context
import com.core.common.android.AndroidUtils
import com.core.common.android.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetVersionUseCase @Inject constructor(
        private val context: Context
//TODO NO USAR CONTEXT
) {

    suspend fun execute(): Resource<String> {
        val versionName = AndroidUtils.getVersionName(context)
        val versionCode = AndroidUtils.getVersionCode(context)
        return Resource.success("$versionName ($versionCode)")
    }
}
