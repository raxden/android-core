package com.core.app.splash

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.splash.databinding.SplashActivityBinding
import com.core.app.splash.view.SplashFragment
import com.core.app.util.OpenForTesting
import com.core.commons.extension.getExtras
import com.core.domain.Forward
import org.jetbrains.annotations.TestOnly

@OpenForTesting
class SplashActivity : AppActivity<SplashViewModel, SplashActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<SplashFragment> {

    companion object {
        fun intent(context: Context): Intent = Intent(context, SplashActivity::class.java)
    }

    private var mSplashFragment: SplashFragment? = null

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: SplashViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
        viewModel.applicationReady.observe(owner, Observer {
            when (it.first) {
                Forward.LOGIN -> navigationHelper.launchLogin(finishCurrentActivity = true)
                Forward.HOME -> it.second?.let { user -> navigationHelper.launchHome(user, finishCurrentActivity = true) }
            }
        })
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): SplashFragment = SplashFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: SplashFragment) { mSplashFragment = fragment }

    @TestOnly
    fun getFragment() = mSplashFragment

}
