package com.core.app.splash.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.SplashFragmentBinding
import com.core.app.splash.SplashViewModel
import com.core.app.splash.databinding.SplashFragmentBinding
import com.core.commons.extension.startFadeInAnimation

class SplashFragment : AppFragment<SplashViewModel, SplashFragmentBinding>() {

    override val viewModelClass: Class<SplashViewModel>
        get() = SplashViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = SplashFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onBindingCreated(binding: SplashFragmentBinding) {
        super.onBindingCreated(binding)
        binding.logoImageView.apply {
            postDelayed({
                visibility = View.VISIBLE
                startFadeInAnimation()
            }, 1000)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: SplashViewModel) {}
}