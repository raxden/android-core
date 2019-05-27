package com.core.app.ui.screens.splash

import android.content.Context
import android.content.Intent
import android.view.View
import com.core.app.AppActivity
import com.core.app.databinding.SplashActivityBinding
import com.core.app.lifecycle.fragment.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.splash.view.SplashFragment
import com.core.commons.extension.getExtras

class SplashActivity : AppActivity<SplashActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<SplashFragment>,
        SplashFragment.FragmentCallback {

    companion object {
        fun intent(context: Context): Intent = Intent(context, SplashActivity::class.java)
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): SplashFragment = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) {}

    // =============== SplashFragment.FragmentCallback =============================================

    override fun launchLogin() {
        navigationHelper.launchLogin(finishCurrentActivity = true)
//        navigationHelper.launchProjectList(finishCurrentActivity = true)
    }
}
