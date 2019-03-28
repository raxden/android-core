package com.core.app.base.mvvm.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.core.app.base.mvvm.BaseViewModel

abstract class BaseViewAdapter<VM : BaseViewModel, VDB : ViewDataBinding, VH : BaseViewHolder<VM, VDB>> : RecyclerView.Adapter<VH>() {

    protected lateinit var mBinding: VDB

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        mBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
//        return BaseViewHolder<>
//    }

}