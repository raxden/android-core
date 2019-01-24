package com.core.app.ui.splash.view

import android.os.Bundle
import com.core.app.AppFragment
import com.core.app.databinding.SplashFragmentBinding
import com.core.app.ui.splash.viewmodel.SplashViewModel

class SplashFragment : AppFragment<SplashViewModel, SplashFragmentBinding, SplashFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback

    override val mViewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel.prepareApplicationToLaunch()
    }

}