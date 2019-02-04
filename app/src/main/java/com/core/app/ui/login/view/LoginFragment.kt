package com.core.app.ui.login.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.LoginFragmentBinding

class LoginFragment : AppFragment<LoginViewModel, LoginFragmentBinding, LoginFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback {
        fun onUserLogged()
    }

    override val mViewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = LoginFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun observeViewModel(viewModel: LoginViewModel) {
        viewModel.userLogged.observe(this, Observer { isLogged ->
            if (isLogged) mCallback.onUserLogged()
        })
    }
}