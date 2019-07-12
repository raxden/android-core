package com.core.app.ui.screens.home.list

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.app.AppFragment
import com.core.app.R
import com.core.app.base.adapter.BaseListAdapter
import com.core.app.databinding.HomeProjectListFragmentBinding
import com.core.app.databinding.HomeProjectListItemBinding
import com.core.app.ui.screens.home.HomeViewModel
import com.core.domain.Project

class HomeProjectListFragment : AppFragment<HomeViewModel, HomeProjectListFragmentBinding>() {

    companion object {
        fun newInstance(bundle: Bundle?) = HomeProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    private lateinit var listAdapter: HomeProjectListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listAdapter = HomeProjectListAdapter(this, viewModel, object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean = oldItem == newItem
        })
    }

    override fun onBindingCreated(binding: HomeProjectListFragmentBinding) {
        super.onBindingCreated(binding)

        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.retrieveProjectList() }
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: HomeViewModel) {
        viewModel.projectList.observe(owner, Observer { listAdapter.submitList(it) })
    }

    internal class HomeProjectListAdapter(
            val fragment: HomeProjectListFragment,
            viewModel: HomeViewModel,
            diffCallback: DiffUtil.ItemCallback<Project>
    ) : BaseListAdapter<Project, HomeViewModel, HomeProjectListItemBinding>(viewModel, diffCallback) {

        override fun getItemViewType(position: Int): Int = R.layout.home_project_list_item
    }
}