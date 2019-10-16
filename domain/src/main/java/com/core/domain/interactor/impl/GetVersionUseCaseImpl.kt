package com.core.domain.interactor.impl

import android.content.Context
import com.core.commons.AppUtils
import com.core.commons.Resource
import com.core.domain.interactor.GetVersionUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetVersionUseCaseImpl @Inject constructor(
        private val context: Context
) : GetVersionUseCase {

    override suspend fun execute(): Resource<String> {
        return Resource.success(AppUtils.getVersionName(context) + " (" + AppUtils.getVersionCode(context) + ")")
    }
}
