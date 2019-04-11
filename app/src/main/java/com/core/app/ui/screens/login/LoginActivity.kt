package com.core.app.ui.screens.login

import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor

class LoginActivity : AppActivity(),
        LoginFragment.FragmentCallback,
        HasInjectFragmentInterceptor<LoginFragment> {

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = findViewById(R.id.content_view)

    override fun onCreateFragment(): LoginFragment = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) {}

    // =============== LoginFragment.FragmentCallback =============================================

    override fun onUserLogged() {
        mNavigationHelper.launchProjectList(finishCurrentView = true)
    }
}
