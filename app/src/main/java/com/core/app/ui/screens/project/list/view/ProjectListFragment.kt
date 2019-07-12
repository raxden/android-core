package com.core.app.ui.screens.project.list.view

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.app.AppFragment
import com.core.app.R
import com.core.app.base.adapter.BaseListAdapter
import com.core.app.databinding.ProjectListFragmentBinding
import com.core.app.databinding.ProjectListItemBinding
import com.core.app.ui.screens.project.list.ProjectListViewModel
import com.core.domain.Project

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding>() {

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override val viewModelClass: Class<ProjectListViewModel>
        get() = ProjectListViewModel::class.java

    private lateinit var listAdapter: ProjectListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter = ProjectListAdapter(this, viewModel, object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem == newItem
        })
    }

    override fun onBindingCreated(binding: ProjectListFragmentBinding) {
        super.onBindingCreated(binding)

        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.retrieveProjectList() }
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProjectListViewModel) {
//        viewModel.ProjectList.observe(owner, Observer { data -> listAdapter.submitList(data) })
//        viewModel.projectSelected.observe(owner, Observer { data -> callback.onProjectSelected(data) })
        viewModel.retrieveProjectList()
    }

    internal class ProjectListAdapter(
            val fragment: ProjectListFragment,
            viewModel: ProjectListViewModel,
            diffCallback: DiffUtil.ItemCallback<Project>
    ) : BaseListAdapter<Project, ProjectListViewModel, ProjectListItemBinding>(viewModel, diffCallback) {

        private var lastAnimatedPosition: Int = -1

        override fun onBindViewHolder(holder: ViewDataBindingHolder<Project, ProjectListViewModel, ProjectListItemBinding>, position: Int) {
            super.onBindViewHolder(holder, position)
            if (position > lastAnimatedPosition) {
                fragment.animationHelper.slideCardFromBottom(holder.itemView)
                lastAnimatedPosition = position
            }
        }

        override fun getItemViewType(position: Int): Int = R.layout.project_list_item
    }
}