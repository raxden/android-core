package com.core.app.base.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.core.app.BR

abstract class BaseViewDataBindingAdapter<T : Any, VDB : ViewDataBinding>
    : RecyclerView.Adapter<BaseViewDataBindingAdapter.ViewDataBindingHolder<T, VDB>>() {

    private var mData: List<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewDataBindingAdapter.ViewDataBindingHolder<T, VDB> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<VDB>(layoutInflater, viewType, parent, false)
        return ViewDataBindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewDataBindingAdapter.ViewDataBindingHolder<T, VDB>, position: Int) {
        getItem(position)?.run { holder.bind(this) }
    }

    abstract override fun getItemViewType(position: Int): Int

    override fun getItemCount(): Int = mData?.size ?: 0

    fun setItems(data: List<T>) {
        if (mData == null) {
            mData = data
            notifyItemRangeInserted(0, data.size)
        }
    }

    fun getItem(position: Int): T? = mData?.get(position)

    fun getItems(): List<T>? = mData

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