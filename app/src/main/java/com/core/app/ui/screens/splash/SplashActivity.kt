package com.core.app.ui.screens.splash

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.databinding.SplashActivityBinding
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.splash.view.SplashFragment
import com.core.commons.extension.getExtras

class SplashActivity : AppActivity<SplashViewModel, SplashActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<SplashFragment> {

    companion object {
        fun intent(context: Context): Intent = Intent(context, SplashActivity::class.java)
    }

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: SplashViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): SplashFragment = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) {}

    // =============== SplashFragment.FragmentCallback =============================================

//    override fun launchLogin() {
//        navigationHelper.launchLogin(finishCurrentActivity = true)
//        navigationHelper.launchProjectList(finishCurrentActivity = true)
//    }
}
