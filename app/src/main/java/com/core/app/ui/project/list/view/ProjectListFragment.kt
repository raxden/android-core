package com.core.app.ui.project.list.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.core.app.AppFragment
import com.core.app.databinding.ProjectListFragmentBinding
import com.core.app.ui.project.list.adapter.ProjectListAdapter

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding, ProjectListFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback

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

        mAdapter = ProjectListAdapter()
        mBinding.recyclerView.adapter = mAdapter
    }

    override fun observeViewModel(viewModel: ProjectListViewModel) {
        viewModel.projectList.observe(this, Observer { data ->
            mAdapter.setData(data)
        })
    }
}