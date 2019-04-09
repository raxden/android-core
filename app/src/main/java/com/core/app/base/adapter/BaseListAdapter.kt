package com.core.app.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.app.BR
import com.core.app.base.BaseViewModel

abstract class BaseListAdapter<T : Any, VM : BaseViewModel, VDB : ViewDataBinding>(
        private val viewModel: VM,
        diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseListAdapter.ViewDataBindingHolder<T, VM, VDB>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewDataBindingHolder<T, VM, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(layoutInflater, viewType, parent, false)
        return ViewDataBindingHolder(viewModel, binding)
    }

    override fun onBindViewHolder(holder: ViewDataBindingHolder<T, VM, VDB>, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    abstract override fun getItemViewType(position: Int): Int

    class ViewDataBindingHolder<T : Any, VM : BaseViewModel, VDB : ViewDataBinding>(
            private val viewModel: VM,
            private val binding: VDB
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.run {
                setVariable(BR.viewModel, viewModel)
                setVariable(BR.position, adapterPosition)
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }
    }
}