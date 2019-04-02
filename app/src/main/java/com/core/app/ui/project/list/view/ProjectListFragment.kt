package com.core.app.ui.project.list.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.app.AppFragment
import com.core.app.R
import com.core.app.base.adapter.BaseViewDataBindingAdapter
import com.core.app.databinding.ProjectListFragmentBinding
import com.core.app.databinding.ProjectListItemBinding
import com.core.domain.Project

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

        mAdapter = ProjectListAdapter(mViewModel)
        mBinding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProjectListViewModel) {
        viewModel.projectList.observe(owner, Observer { data -> mAdapter.setItems(data) })
        viewModel.projectSelected.observe(owner, Observer { data -> mCallback.onProjectSelected(data) })
    }

    internal class ProjectListAdapter(val mViewModel: ProjectListViewModel) : BaseViewDataBindingAdapter<Project, ProjectListItemBinding>() {

        override fun getItemViewType(position: Int): Int = R.layout.project_list_item

        override fun onBindViewHolder(holder: ViewDataBindingHolder<Project, ProjectListItemBinding>, position: Int) {
            super.onBindViewHolder(holder, position)
            holder.binding.root.setOnClickListener { mViewModel.itemSelected(position) }
        }
    }
}