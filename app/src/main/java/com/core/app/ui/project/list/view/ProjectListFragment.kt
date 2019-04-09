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
import com.core.app.model.ProjectModel
import com.core.domain.Project

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding, ProjectListFragment.FragmentCallback>() {

    interface FragmentCallback : AppFragmentCallback {
        fun onProjectSelected(project: Project)
    }

    private lateinit var mAdapter: ProjectModelListAdapter

    override val mViewModelClass: Class<ProjectListViewModel>
        get() = ProjectListViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = ProjectModelListAdapter(mViewModel, object : DiffUtil.ItemCallback<ProjectModel>() {
            override fun areItemsTheSame(oldItem: ProjectModel, newItem: ProjectModel): Boolean = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: ProjectModel, newItem: ProjectModel): Boolean = oldItem == newItem
        })
        mViewDataBinding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProjectListViewModel) {
        viewModel.projectModelList.observe(owner, Observer { data -> mAdapter.submitList(data) })
        viewModel.projectSelected.observe(owner, Observer { data -> mCallback.onProjectSelected(data) })
    }

    internal class ProjectModelListAdapter(
            viewModel: ProjectListViewModel,
            diffCallback: DiffUtil.ItemCallback<ProjectModel>
    ) : BaseListAdapter<ProjectModel, ProjectListViewModel, ProjectListItemBinding>(viewModel, diffCallback) {

        override fun getItemViewType(position: Int): Int = R.layout.project_list_item
    }
}