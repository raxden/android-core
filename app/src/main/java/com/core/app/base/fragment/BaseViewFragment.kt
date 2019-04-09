package com.core.app.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.commons.extension.getLayoutId
import javax.inject.Inject

abstract class BaseViewFragment<VDB : ViewDataBinding, TCallback : BaseViewFragment.BaseViewFragmentCallback>
    : BaseFragment<TCallback>() {

    interface BaseViewFragmentCallback : BaseFragmentCallback

    protected lateinit var mViewDataBinding: VDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<VDB>(inflater, getLayoutId(), container, false).let { binding ->
            mViewDataBinding = binding
            binding.lifecycleOwner = viewLifecycleOwner    // Layout requirement to listen any changes on LiveData values
            onViewDataBindingCreated(binding)
            binding.root
        }
    }

    abstract fun onViewDataBindingCreated(binding: VDB)
}