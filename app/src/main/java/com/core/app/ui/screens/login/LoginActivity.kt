package com.core.app.ui.screens.login

import android.view.View
import androidx.appcompat.app.AlertDialog
import com.core.app.AppActivity
import com.core.app.databinding.LoginActivityBinding
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.commons.extension.getExtras
import com.core.domain.Account
import com.core.domain.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor

class LoginActivity : AppActivity<LoginActivityBinding>(),
        LoginFragment.FragmentCallback,
        HasInjectFragmentInterceptor<LoginFragment> {

    var mLoginFragment: LoginFragment? = null

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = mBinding.contentView

    override fun onCreateFragment(): LoginFragment = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) {
        mLoginFragment = fragment
    }

    // =============== LoginFragment.FragmentCallback =============================================

    override fun onLoginSuccess(account: Account) {
        mNavigationHelper.launchProjectList(finishCurrentView = true)
    }
}
