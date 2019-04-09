package com.core.app

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.core.app.base.BaseViewModel
import com.core.app.base.fragment.BaseViewModelFragment

abstract class AppFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : AppFragment.AppFragmentCallback>
    : BaseViewModelFragment<VM, VDB, TCallback>() {

    interface AppFragmentCallback : BaseViewModelFragmentCallback

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM) {}
}