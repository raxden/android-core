package com.core.app

import com.core.app.base.BaseFragmentActivity
import com.raxdenstudios.square.interceptor.HasInterceptor
import com.raxdenstudios.square.interceptor.Interceptor

abstract class AppActivity : BaseFragmentActivity(), HasInterceptor {

    override fun onInterceptorCreated(interceptor: Interceptor) {}
}