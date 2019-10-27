package com.core.features.splash

import android.view.View
import com.core.BaseActivity
import com.core.lifecycle.activity.InjectFragmentActivityLifecycle

class SplashActivity : BaseActivity(), InjectFragmentActivityLifecycle.Callback<SplashFragment> {

    override val layoutId: Int
        get() = R.layout.splash_activity

    override fun onLoadFragmentContainer(): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateFragment(): SplashFragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentLoaded(fragment: SplashFragment) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
