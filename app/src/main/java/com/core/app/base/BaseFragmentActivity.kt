package com.core.app.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.core.app.BR
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import javax.inject.Inject

abstract class BaseFragmentActivity : BaseActivity(),
    AutoInflateLayoutInterceptorCallback {

    @Inject
    lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    lateinit var mContentView: View

    // ========= AutoInflateLayoutInterceptorCallback ==============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mAutoInflateLayoutInterceptor)
    }
}