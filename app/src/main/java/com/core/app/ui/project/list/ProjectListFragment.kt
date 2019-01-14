package com.core.app.ui.project.list

import android.os.Bundle
import com.core.app.base.mvvm.BaseViewFragment
import com.core.app.databinding.ProjectListFragmentBinding
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import javax.inject.Inject

class ProjectListFragment : BaseViewFragment<ProjectListViewModel, ProjectListFragmentBinding>(),
        AutoInflateViewInterceptorCallback {

    @Inject
    lateinit var mProjectListViewModel: ProjectListViewModel

    override fun getViewModel(): ProjectListViewModel = mProjectListViewModel

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }

}