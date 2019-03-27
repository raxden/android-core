package com.core.app.ui.splash

import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.ui.splash.view.SplashFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor

class SplashActivity : AppActivity(),
        SplashFragment.FragmentCallback,
        HasInjectFragmentInterceptor<SplashFragment> {

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = mContentView.findViewById(R.id.content_view)

    override fun onCreateFragment(): SplashFragment = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) {}

    // =============== SplashFragment.FragmentCallback =============================================

    override fun launchLogin() {
        mNavigationHelper.launchLogin(finishCurrentView = true)
    }
}
