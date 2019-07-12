package com.core.app

import androidx.databinding.ViewDataBinding
import com.core.app.base.BaseViewModel
import com.core.app.base.activity.BaseViewModelFragmentActivity

abstract class AppActivity<VM : BaseViewModel, VDB : ViewDataBinding> : BaseViewModelFragmentActivity<VM, VDB>() {

    override val layoutId: Int
        get() = javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
                .takeIf { it.isNotEmpty() }?.let {
                    resources.getIdentifier(it.replace("R.layout.", ""), "layout", packageName)
                } ?: 0
}

