package com.core.app.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.app.BR

abstract class BaseListAdapter<T : Any, VDB : ViewDataBinding>(
        diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter.ViewDataBindingHolder<T, VDB>>(diffCallback) {

    private var mData: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewDataBindingHolder<T, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(layoutInflater, viewType, parent, false)
        return ViewDataBindingHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewDataBindingHolder<T, VDB>, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    abstract override fun getItemViewType(position: Int): Int

    class ViewDataBindingHolder<T : Any, VDB : ViewDataBinding> constructor(val binding: VDB)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.run {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }
}