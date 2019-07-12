package com.core.app.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.BaseViewModel
import javax.inject.Inject

abstract class BaseViewModelFragment<VM : BaseViewModel, VDB : ViewDataBinding>
    : BaseViewFragment<VDB>() {

//    interface OnViewModelAttached {
//        fun onAttached()
//    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>
    lateinit var viewModel: VM

//    private var viewModelAttachedListenerList : MutableList<OnViewModelAttached> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass).also { it.onAttached() }
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
//        viewModelAttachedListenerList.forEach { it.onAttached() }
    }

//    override fun onDestroy() {
//        viewModelAttachedListenerList.clear()
//        super.onDestroy()
//    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)

//    fun addOnViewModelAttachedListener(listener: OnViewModelAttached) {
//        viewModelAttachedListenerList.add(listener)
//    }
//
//    fun removeOnViewModelAttachedListener(listener: OnViewModelAttached) {
//        viewModelAttachedListenerList.remove(listener)
//    }
}