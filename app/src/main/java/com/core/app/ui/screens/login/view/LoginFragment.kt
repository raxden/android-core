package com.core.app.ui.screens.login.view

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.LoginFragmentBinding
import com.core.domain.User

class LoginFragment : AppFragment<LoginViewModel, LoginFragmentBinding, LoginFragment.FragmentCallback>() {

    interface FragmentCallback : AppFragmentCallback {
        fun onUserLogged(user: User)
    }

    override val mViewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = LoginFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
        viewModel.userLogged.observe(owner, Observer { user ->
            mCallback.onUserLogged(user)
        })
    }
}