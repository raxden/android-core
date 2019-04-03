package com.core.app.base

import android.view.View
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor

abstract class BaseFragmentActivity : BaseActivity(),
        HasAutoInflateLayoutInterceptor {

    lateinit var mRootView: View

    // ========= HasInflateLayoutInterceptor =======================================================

    override fun onContentViewCreated(view: View) {
        mRootView = view
    }

    // =============== Support methods =============================================================

    override fun onInterceptorCreated(interceptor: Interceptor) {}
}