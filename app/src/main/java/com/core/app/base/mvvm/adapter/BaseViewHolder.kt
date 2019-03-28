package com.core.app.base.mvvm.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.core.app.BR
import com.core.app.base.mvvm.BaseViewModel

abstract class BaseViewHolder<VM : BaseViewModel, VDB : ViewDataBinding> constructor(
        private val viewDataBinding: VDB
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(viewModel: VM, position: Int) {
        viewDataBinding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
        }
    }
}