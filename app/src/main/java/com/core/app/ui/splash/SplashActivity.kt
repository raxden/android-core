package com.core.app.ui.splash

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.base.BaseFragmentActivity
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.fullscreen.FullScreenInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import kotlinx.android.synthetic.main.splash_activity.*
import javax.inject.Inject

class SplashActivity : AppActivity(),
    InjectFragmentInterceptorCallback<SplashFragment> {

    @Inject
    internal lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor
    @Inject
    internal lateinit var mFullScreenInterceptor: FullScreenInterceptor

    // =============== InjectFragmentInterceptorCallback ===========================================

    override fun onCreateFragment(): SplashFragment? = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) {}

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = mContentView.findViewById(R.id.content_view)

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mInjectFragmentInterceptor)
        interceptorList.add(mFullScreenInterceptor)
    }
}
