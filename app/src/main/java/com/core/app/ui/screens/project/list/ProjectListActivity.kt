package com.core.app.ui.screens.project.list

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.databinding.ProjectListActivityBinding
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.lifecycle.activity.ToolbarActivityLifecycle
import com.core.app.ui.screens.project.list.view.ProjectListFragment
import com.core.commons.extension.alignToStatusBarBottom
import com.core.commons.extension.getExtras
import com.core.domain.User

class ProjectListActivity : AppActivity<ProjectListViewModel, ProjectListActivityBinding>(),
        ToolbarActivityLifecycle.Callback,
        InjectFragmentActivityLifecycle.Callback<ProjectListFragment> {

    companion object {
        fun intent(context: Context, user: User): Intent = Intent(context, ProjectListActivity::class.java).apply {
            putExtra(User::class.java.simpleName, user)
        }
    }

    override val viewModelClass: Class<ProjectListViewModel>
        get() = ProjectListViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: ProjectListViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
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

//    override fun onProjectSelected(project: Project) {
//        Timber.d("onProjectSelected ${project.name}")
//    }
}