package com.core.app.base.mvvm

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.base.BaseFragment
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import javax.inject.Inject

abstract class BaseViewFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback : BaseViewFragment.BaseViewFragmentCallback> : BaseFragment<TCallback>(),
        AutoInflateViewInterceptorCallback {

    interface BaseViewFragmentCallback : BaseFragmentCallback

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAutoInflateViewInterceptor: AutoInflateViewInterceptor

    protected abstract val mViewModelClass: Class<VM>
    private lateinit var mViewModel: VM
    protected var mBinding: VDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(mViewModelClass).also { it.onCreated() }
        observeViewModel(mViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = DataBindingUtil.bind(view)
        mBinding?.apply {
            setLifecycleOwner(this@BaseViewFragment)    // Layout requirement to listen any changes on LiveData values
            setVariable(BR.viewModel, mViewModel)
            executePendingBindings()
        }
    }

    protected abstract fun observeViewModel(viewModel: VM)

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mAutoInflateViewInterceptor)
    }
}