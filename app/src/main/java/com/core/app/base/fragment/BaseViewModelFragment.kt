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
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass).also { it.onCreated() }
    }

    override fun onBindingCreated(binding: VDB) {
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onViewModelAttached(viewLifecycleOwner, viewModel)
    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)
}