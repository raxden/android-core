package com.core.app.base.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseFragmentActivity<VDB : ViewDataBinding> : BaseActivity() {

    protected abstract val layoutId: Int
    protected lateinit var binding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        onBindingCreated(binding)

        super.onCreate(savedInstanceState)
    }

    abstract fun onBindingCreated(binding: VDB)
}