package com.core.app.ui.screens.splash.view

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.SplashFragmentBinding
import com.core.app.ui.screens.splash.SplashViewModel

class SplashFragment : AppFragment<SplashViewModel, SplashFragmentBinding>() {

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: SplashViewModel) {}
}