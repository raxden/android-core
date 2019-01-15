package com.core.app.ui.project.list.view

import android.os.Bundle
import com.core.app.AppFragment
import com.core.app.databinding.ProjectListFragmentBinding
import com.core.app.ui.project.list.viewModel.ProjectListViewModel
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback

class ProjectListFragment : AppFragment<ProjectListViewModel, ProjectListFragmentBinding>(),
        AutoInflateViewInterceptorCallback {

    override fun getViewModel(): Class<ProjectListViewModel> = ProjectListViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }


}