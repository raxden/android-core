package com.core.app

import androidx.databinding.ViewDataBinding
import com.core.app.base.activity.BaseFragmentActivity
import com.raxdenstudios.square.interceptor.HasInterceptor
import com.raxdenstudios.square.interceptor.Interceptor

abstract class AppActivity<VDB : ViewDataBinding> : BaseFragmentActivity<VDB>(), HasInterceptor {

    override val mLayoutId: Int
        get() = getLayoutId()

    override fun onBindingCreated(binding: VDB) {}

    override fun onInterceptorCreated(interceptor: Interceptor) {}

    private fun getLayoutId(): Int = javaClass.simpleName
            .decapitalize()
            .split("(?=\\p{Upper})".toRegex())
            .joinToString(separator = "_")
            .toLowerCase()
            .takeIf { it.isNotEmpty() }?.let {
                resources.getIdentifier(it.replace("R.layout.", ""), "layout", packageName)
            } ?: 0
}

