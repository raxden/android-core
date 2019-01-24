package com.core.app.base.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.app.BR
import com.core.app.R
import com.core.app.base.BaseFragment
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import timber.log.Timber
import javax.inject.Inject

abstract class BaseViewFragment<VM : BaseViewModel, VDB : ViewDataBinding, TCallback: BaseViewFragment.BaseViewFragmentCallback> : BaseFragment<TCallback>(),
        AutoInflateViewInterceptorCallback {

    interface BaseViewFragmentCallback : BaseFragmentCallback

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAutoInflateViewInterceptor: AutoInflateViewInterceptor

    protected abstract val mViewModelClass: Class<VM>
    protected val mViewModel: VM by lazy { ViewModelProvider(this, mViewModelFactory).get(mViewModelClass) }
    protected var mViewDataBinding: VDB? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = DataBindingUtil.bind(view)
        mViewDataBinding?.apply {
            setLifecycleOwner(this@BaseViewFragment)    // Layout requirement to listen any changes on LiveData values
            setVariable(BR.viewModel, mViewModel)
            executePendingBindings()
        }
    }

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mAutoInflateViewInterceptor)
    }
}