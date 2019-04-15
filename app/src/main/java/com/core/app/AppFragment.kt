package com.core.app

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.core.app.base.BaseViewModel
import com.core.app.base.fragment.BaseViewModelFragment

abstract class AppFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : AppFragment.AppFragmentCallback>
    : BaseViewModelFragment<VM, VDB, TCallback>() {

    interface AppFragmentCallback : BaseViewModelFragmentCallback

    override val mLayoutId: Int
        get() = getLayoutId()

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM) {}

    private fun getLayoutId(): Int = javaClass.simpleName
            .decapitalize()
            .split("(?=\\p{Upper})".toRegex())
            .joinToString(separator = "_")
            .toLowerCase()
            .takeIf { it.isNotEmpty() }?.let {
                resources.getIdentifier(it.replace("R.layout.", ""), "layout", context?.packageName)
            } ?: 0
}