package com.core.app.ui.screens.login

import android.content.Context
import android.content.Intent
import android.view.View
import com.core.app.AppActivity
import com.core.app.databinding.LoginActivityBinding
import com.core.app.lifecycle.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.commons.extension.getExtras

class LoginActivity : AppActivity<LoginActivityBinding>(),
        InjectFragmentActivityLifecycle.Callback<LoginFragment>,
        LoginFragment.FragmentCallback {

    companion object {
        fun intent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    var loginFragment: LoginFragment? = null

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): LoginFragment = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) {
        loginFragment = fragment
    }

    // =============== LoginFragment.FragmentCallback =============================================

    override fun onLoginSuccess() {
        navigationHelper.launchProjectList()
    }
}
