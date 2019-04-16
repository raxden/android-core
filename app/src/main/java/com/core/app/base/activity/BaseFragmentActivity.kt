package com.core.app.base.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.core.app.base.activity.BaseActivity
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.HasAutoInflateLayoutInterceptor

abstract class BaseFragmentActivity<VDB : ViewDataBinding> : BaseActivity() {

    protected abstract val mLayoutId: Int
    protected lateinit var mBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.setContentView(this, mLayoutId)
        mBinding.lifecycleOwner = this
        onBindingCreated(mBinding)

        super.onCreate(savedInstanceState)
    }

    abstract fun onBindingCreated(binding: VDB)
}