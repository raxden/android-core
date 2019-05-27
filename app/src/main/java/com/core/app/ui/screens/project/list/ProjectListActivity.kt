package com.core.app.ui.screens.project.list

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.core.app.AppActivity
import com.core.app.databinding.ProjectListActivityBinding
import com.core.app.lifecycle.InjectFragmentActivityLifecycle
import com.core.app.lifecycle.ToolbarActivityLifecycle
import com.core.app.ui.screens.project.list.view.ProjectListFragment
import com.core.commons.extension.alignToStatusBarBottom
import com.core.commons.extension.getExtras
import com.core.domain.Project
import timber.log.Timber

class ProjectListActivity : AppActivity<ProjectListActivityBinding>(),
        ToolbarActivityLifecycle.Callback,
        InjectFragmentActivityLifecycle.Callback<ProjectListFragment>,
        ProjectListFragment.FragmentCallback {

    companion object {
        fun intent(context: Context): Intent = Intent(context, ProjectListActivity::class.java)
    }

    // =============== HasToolbarInterceptor =======================================================

    override fun onCreateToolbarView(): Toolbar = binding.toolbarView

    override fun onToolbarViewCreated(toolbar: Toolbar) {
        toolbar.alignToStatusBarBottom()
    }

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): ProjectListFragment = ProjectListFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: ProjectListFragment) {}

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onProjectSelected(project: Project) {
        Timber.d("onProjectSelected ${project.name}")
    }
}