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

    override fun onResume() {
        super.onResume()

        MaterialAlertDialogBuilder(this)
                .setTitle("titule")
                .setMessage("message")
                .create()
                .show()

//        AlertDialog.Builder(this)
//                .setTitle("titule")
//                .setMessage("message")
//                .create()
//                .show()

//        AlertDialog.Builder(mActivity).apply {
//            setTitle(error.title)
//            setMessage(error.message)
//            setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
//            setOnDismissListener { dialog ->
//                mErrorList.remove(mErrorList.first())
//                if (mErrorList.isNotEmpty()) showError(mErrorList.first())
//            }
//        }.show()
    }

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
