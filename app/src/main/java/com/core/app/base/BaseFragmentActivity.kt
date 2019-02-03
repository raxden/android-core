package com.core.app.base

import android.os.Bundle
import android.view.View
import com.core.app.observer.TrackerObserver
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback
import javax.inject.Inject

abstract class BaseFragmentActivity : BaseActivity(),
        AutoInflateLayoutInterceptorCallback {

    lateinit var mTrackerObserver: TrackerObserver

    @Inject
    lateinit var mAutoInflateLayoutInterceptor: AutoInflateLayoutInterceptor
    lateinit var mContentView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mTrackerObserver = TrackerObserver()
        lifecycle.addObserver(mTrackerObserver)
    }

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