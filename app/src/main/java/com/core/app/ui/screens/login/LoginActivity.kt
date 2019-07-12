package com.core.app.ui.screens.login

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.databinding.LoginActivityBinding
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.app.ui.screens.splash.SplashViewModel
import com.core.commons.extension.getExtras

class LoginActivity : AppActivity<LoginViewModel, LoginActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<LoginFragment> {

    companion object {
        fun intent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): LoginFragment = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) {}

    // =============== LoginFragment.FragmentCallback =============================================
//
//    override fun onLoginSuccess() {
//        navigationHelper.launchProjectList()
//    }
}
