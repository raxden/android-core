package com.core.app.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : BaseViewModelFragment.BaseViewModelFragmentCallback>
    : BaseViewFragment<VDB, TCallback>() {

    interface BaseViewModelFragmentCallback : BaseViewFragmentCallback

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected abstract val mViewModelClass: Class<VM>
    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(mViewModelClass).also { it.onCreated() }
    }

    override fun onViewDataBindingCreated(binding: VDB) {
        binding.apply {
            setVariable(BR.viewModel, mViewModel)
            executePendingBindings()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewModelAttached(viewLifecycleOwner, mViewModel)
    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)
}