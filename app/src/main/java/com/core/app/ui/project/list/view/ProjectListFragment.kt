package com.core.app.ui.project.list.view

import android.os.Bundle
import com.core.app.AppFragment
import com.core.app.databinding.ProjectListFragmentBinding

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding, ProjectListFragment.FragmentCallback>() {

    interface FragmentCallback : BaseViewFragmentCallback

    override val mViewModelClass: Class<ProjectListViewModel>
        get() = ProjectListViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

    override fun observeViewModel(viewModel: ProjectListViewModel) {

    }
}