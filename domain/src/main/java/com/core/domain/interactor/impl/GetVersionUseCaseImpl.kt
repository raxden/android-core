package com.core.domain.interactor.impl

import android.content.Context
import com.core.domain.interactor.GetVersionUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetVersionUseCaseImpl @Inject constructor(
        private val context: Context
) : GetVersionUseCase {

    override fun execute(): Single<String> = Single.just(AppUtils.getVersionName(context) + " (" + AppUtils.getVersionCode(context) + ")")
}
