package com.core.app.base.activity

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelFragmentActivity<VM : BaseViewModel, VDB : ViewDataBinding>
    : BaseFragmentActivity<VDB>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>
    protected val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory).get(viewModelClass).also { it.onAttached() }
    }

    override fun onCreateBinding() {
        super.onCreateBinding()

        onViewModelAttached(this, viewModel)
    }

    override fun onBindingCreated(binding: VDB) {
        binding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)
}