package com.core.app

import androidx.databinding.ViewDataBinding
import com.core.app.base.BaseViewModel
import com.core.app.base.fragment.BaseViewModelFragment

abstract class AppFragment<VM : com.core.app.base.BaseViewModel, VDB : ViewDataBinding>
    : BaseViewModelFragment<VM, VDB>() {

    override val layoutId: Int
        get() = javaClass.simpleName
                .decapitalize()
                .split("(?=\\p{Upper})".toRegex())
                .joinToString(separator = "_")
                .toLowerCase()
                .takeIf { it.isNotEmpty() }?.let {
                    resources.getIdentifier(it.replace("R.layout.", ""), "layout", context?.packageName)
                } ?: 0
}