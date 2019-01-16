package com.core.app.ui.splash.view

import android.os.Bundle
import com.core.app.AppFragment
import com.core.app.databinding.SplashFragmentBinding
import com.core.app.ui.splash.viewModel.SplashViewModel

class SplashFragment : AppFragment<SplashViewModel, SplashFragmentBinding>() {

//    override fun getViewModel(): Class<SplashViewModel> = SplashViewModel::class.java

    override val mViewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }
}