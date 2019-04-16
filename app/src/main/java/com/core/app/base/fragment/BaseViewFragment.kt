package com.core.app.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseViewFragment<VDB : ViewDataBinding, TCallback : BaseViewFragment.BaseViewFragmentCallback>
    : BaseFragment<TCallback>() {

    interface BaseViewFragmentCallback : BaseFragmentCallback

    protected abstract val mLayoutId: Int
    lateinit var mBinding: VDB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return DataBindingUtil.inflate<VDB>(inflater, mLayoutId, container, false).let { binding ->
            mBinding = binding
            binding.lifecycleOwner = viewLifecycleOwner    // Layout requirement to listen any changes on LiveData values
            onBindingCreated(binding)
            binding.root
        }
    }

    abstract fun onBindingCreated(binding: VDB)
}