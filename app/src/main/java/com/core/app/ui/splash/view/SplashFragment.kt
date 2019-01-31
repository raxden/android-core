package com.core.app.ui.splash.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.SplashFragmentBinding

class SplashFragment : AppFragment<SplashViewModel, SplashFragmentBinding, SplashFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback {
        fun launchLogin()
    }

    override val mViewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun observeViewModel(viewModel: SplashViewModel) {
        viewModel.isApplicationReadyToLaunch().observe(this, Observer { isReady ->
            if (isReady) mCallback.launchLogin()
        })
    }
}