package com.core.app.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.BaseFragment
import com.core.commons.extension.getLayoutId
import javax.inject.Inject

abstract class BaseViewFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : BaseViewFragment.BaseViewFragmentCallback>
    : BaseFragment<TCallback>() {

    interface BaseViewFragmentCallback : BaseFragmentCallback

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    protected abstract val mViewModelClass: Class<VM>
    protected lateinit var mViewModel: VM
    protected lateinit var mBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(mViewModelClass).also { it.onCreated() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate<VDB>(inflater, getLayoutId(), container, false).apply {
            lifecycleOwner = viewLifecycleOwner    // Layout requirement to listen any changes on LiveData values
            setVariable(BR.viewModel, mViewModel)
            executePendingBindings()
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewModelAttached(viewLifecycleOwner, mViewModel)
    }

    abstract fun onViewModelAttached(owner: LifecycleOwner, viewModel: VM)

    fun getViewModel(): VM = mViewModel
}