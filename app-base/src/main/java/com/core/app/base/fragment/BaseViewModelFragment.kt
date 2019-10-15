package com.core.app.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : BaseViewModel, VDB : ViewDataBinding>
    : BaseViewFragment<VDB>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass).also { it.onAttached() }
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