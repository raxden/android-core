package com.core.app.ui.screens.login.view

import android.Manifest
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.LoginFragmentBinding
import com.core.app.util.PermissionManager
import com.core.domain.User
import com.tbruyelle.rxpermissions2.Permission

class LoginFragment : AppFragment<LoginViewModel, LoginFragmentBinding, LoginFragment.FragmentCallback>(),
        PermissionManager.Callback {

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

    override fun onBindingCreated(binding: LoginFragmentBinding) {
        super.onBindingCreated(binding)
        binding.accessFineLocationBtn.setOnClickListener {
            mPermissionManager.requestPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        }
        binding.cameraBtn.setOnClickListener {
            mPermissionManager.requestPermission(this, Manifest.permission.CAMERA)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: LoginViewModel) {
        viewModel.userLogged.observe(owner, Observer { user ->
            mCallback.onUserLogged(user)
        })
    }

    override fun onPermissionGranted(permission: Permission) {

    }

    override fun onPermissionDenied(permission: Permission) {

    }
}