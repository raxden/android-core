package com.core.app.ui.project.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.core.app.R
import com.core.app.base.mvvm.BaseViewFragment
import com.core.app.databinding.ProjectListItemBinding
import com.core.domain.Project

class ProjectListAdapter : RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>() {

    interface AdapterCallback {
        fun onItemPressed(item: Project)
    }

    private var mData: List<Project>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ProjectListItemBinding>(layoutInflater, viewType, parent, false)
        return ProjectListViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int = R.layout.project_list_item

    override fun getItemCount(): Int = mData?.size ?: 0

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
        holder.binding.apply {
            project = mData?.get(position)
            executePendingBindings()
        }
    }

    fun setItems(data: List<Project>) {
        if (mData == null) {
            mData = data
            notifyItemRangeInserted(0, data.size)
        }
    }

    class ProjectListViewHolder constructor(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}