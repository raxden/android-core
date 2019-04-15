package com.core.app.ui.screens.project.list

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.databinding.ProjectListActivityBinding
import com.core.app.ui.screens.project.list.view.ProjectListFragment
import com.core.commons.extension.alignToStatusBarBottom
import com.core.commons.extension.getExtras
import com.core.domain.Project
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.toolbar.HasToolbarInterceptor
import timber.log.Timber

class ProjectListActivity : AppActivity<ProjectListActivityBinding>(),
        ProjectListFragment.FragmentCallback,
        HasToolbarInterceptor,
        HasInjectFragmentInterceptor<ProjectListFragment> {

    // =============== HasToolbarInterceptor =======================================================

    override fun onCreateToolbarView(): Toolbar = mBinding.toolbarView

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        toolbar.alignToStatusBarBottom()
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = mBinding.contentView

    override fun onCreateFragment(): ProjectListFragment = ProjectListFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: ProjectListFragment) {}

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onProjectSelected(project: Project) {
        Timber.d("onProjectSelected ${project.name}")
    }
}