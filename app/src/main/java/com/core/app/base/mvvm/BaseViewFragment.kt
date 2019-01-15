package com.core.app.base.mvvm

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.app.base.BaseFragment
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseViewFragment<VM : ViewModel, VDB : ViewDataBinding> : BaseFragment(),
        AutoInflateViewInterceptorCallback {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mAutoInflateViewInterceptor: AutoInflateViewInterceptor

    private var mViewModel: VM? = null
    private var mViewDataBinding: VDB? = null

    abstract fun getViewModel(): Class<VM>

    fun getViewDataBinding() : VDB? = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(this, mViewModelFactory).get(getViewModel())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = DataBindingUtil.bind(view)
    }

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.apply {
            add(mAutoInflateViewInterceptor)
        }
    }

}