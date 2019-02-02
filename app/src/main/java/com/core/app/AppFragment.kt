package com.core.app

import androidx.databinding.ViewDataBinding
import com.core.app.base.mvvm.BaseViewFragment
import com.core.app.base.mvvm.BaseViewModel

abstract class AppFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : BaseViewFragment.BaseViewFragmentCallback> : BaseViewFragment<VM, VDB, TCallback>() {

    override fun observeViewModel(viewModel: VM) {}
}