package com.core.app.ui.screens.splash

import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.databinding.SplashActivityBinding
import com.core.app.ui.screens.splash.view.SplashFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor

class SplashActivity : AppActivity<SplashActivityBinding>(),
        SplashFragment.FragmentCallback,
        HasInjectFragmentInterceptor<SplashFragment> {

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = mBinding.contentView

    override fun onCreateFragment(): SplashFragment = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) {}

    // =============== SplashFragment.FragmentCallback =============================================

    override fun launchLogin() {
        mNavigationHelper.launchLogin(finishCurrentView = true)
//        mNavigationHelper.launchProjectList(finishCurrentView = true)
    }
}
