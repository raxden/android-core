package com.core.app.ui.project.list

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.core.app.base.mvvm.BaseViewFragment
import com.core.app.databinding.ProjectListFragmentBinding
import com.raxdenstudios.square.interceptor.commons.autoinflateview.AutoInflateViewInterceptorCallback
import javax.inject.Inject

class ProjectListFragment : BaseViewFragment<ProjectListViewModel, ProjectListFragmentBinding>(),
        AutoInflateViewInterceptorCallback {

    override fun getViewModel(): Class<ProjectListViewModel> = ProjectListViewModel::class.java

    companion object {
        fun newInstance(bundle: Bundle?) = ProjectListFragment().apply {
            arguments = bundle ?: Bundle()
        }
    }


}