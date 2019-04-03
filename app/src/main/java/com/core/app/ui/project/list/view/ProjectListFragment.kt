package com.core.app.ui.project.list.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.app.AppFragment
import com.core.app.R
import com.core.app.base.adapter.BaseListAdapter
import com.core.app.databinding.ProjectListFragmentBinding
import com.core.app.databinding.ProjectListItemBinding
import com.core.domain.Project
import timber.log.Timber

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding, ProjectListFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback {
        fun onProjectSelected(project: Project)
    }

    private lateinit var mAdapter: ProjectListAdapter

    override val mViewModelClass: Class<ProjectListViewModel>
        get() = ProjectListViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ProjectListAdapter(mViewModel, object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem == newItem
        })
        mBinding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProjectListViewModel) {
        viewModel.projectList.observe(owner, Observer { data -> mAdapter.submitList(data) })
        viewModel.projectSelected.observe(owner, Observer { data -> mCallback.onProjectSelected(data) })
    }

    internal class ProjectListAdapter(
            viewModel: ProjectListViewModel,
            diffCallback: DiffUtil.ItemCallback<Project>
    ) : BaseListAdapter<Project, ProjectListViewModel, ProjectListItemBinding>(viewModel, diffCallback) {

        override fun getItemViewType(position: Int): Int = R.layout.project_list_item

        override fun onBindViewHolder(holder: ViewDataBindingHolder<Project, ProjectListViewModel, ProjectListItemBinding>, position: Int) {
            super.onBindViewHolder(holder, position)
            holder.itemView.setOnClickListener { Timber.d("") }
        }
    }
}