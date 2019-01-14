package com.core.app.base.mvvm

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.core.app.base.BaseFragment
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import javax.inject.Inject

abstract class BaseViewFragment<VM : BaseViewModel, VDB : ViewDataBinding> : BaseFragment(),
        AutoInflateViewInterceptorCallback {

    @Inject
    lateinit var mAutoInflateViewInterceptor: AutoInflateViewInterceptor

    private var mViewModel: VM? = null
    private var mViewDataBinding: VDB? = null

    abstract fun getViewModel(): VM

    fun getViewDataBinding() : VDB? = mViewDataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = DataBindingUtil.bind(view)
    }

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList.apply {
            add(mAutoInflateViewInterceptor)
        })
    }

}