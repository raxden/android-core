package com.core.app.ui.screens.login.view

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.LoginFragmentBinding
import com.core.app.ui.screens.login.LoginViewModel

class LoginFragment : AppFragment<LoginViewModel, LoginFragmentBinding>() {

    override val viewModelClass: Class<LoginViewModel>
        get() = LoginViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = LoginFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
//        viewModel.userLogged.observe(owner, Observer { callback.onLoginSuccess() })
    }
}