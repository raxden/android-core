package com.core.app.ui.screens.home

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.core.app.AppActivity
import com.core.app.databinding.HomeActivityBinding
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.lifecycle.activity.ToolbarActivityLifecycle
import com.core.app.ui.screens.home.list.HomeProjectListFragment
import com.core.commons.extension.getExtras
import com.core.domain.User

class HomeActivity : AppActivity<HomeViewModel, HomeActivityBinding>(),
        ToolbarActivityLifecycle.Callback,
        InjectFragmentActivityLifecycle.Callback<HomeProjectListFragment> {

    companion object {
        fun intent(context: Context, user: User): Intent = Intent(context, HomeActivity::class.java).apply {
            putExtra(User::class.java.simpleName, user)
        }
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun onViewModelAttached(owner: LifecycleOwner, viewModel: HomeViewModel) {
        viewModel.throwable.observe(owner, Observer { errorManager.set(it) })
        viewModel.projectSelected.observe(owner, Observer {
            it.getContentIfNotHandled()?.let { project -> navigationHelper.launchProject(project) }
        })
    }

    // =============== HasToolbarInterceptor =======================================================

    override fun onCreateToolbarView(): Toolbar = binding.toolbarView

    override fun onToolbarViewCreated(toolbar: Toolbar) {}

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = binding.contentView

    override fun onCreateFragment(): HomeProjectListFragment = HomeProjectListFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: HomeProjectListFragment) {}
}