package com.core.app.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutInterceptor
import com.raxdenstudios.square.interceptor.commons.inflatelayout.InflateLayoutInterceptorCallback
import javax.inject.Inject

abstract class BaseFragmentActivity<VDB : ViewDataBinding> : BaseActivity(),
        InflateLayoutInterceptorCallback {

    @Inject
    lateinit var mInflateLayoutInterceptor: InflateLayoutInterceptor

    protected var mBinding: VDB? = null

    // ========= AutoInflateLayoutInterceptorCallback ==============================================

    override fun onLayoutIdLoaded(layoutId: Int, savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.setContentView(this, layoutId)
    }

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mInflateLayoutInterceptor)
    }
}