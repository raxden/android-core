package com.core.app.ui.project.list

import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.ui.project.list.view.ProjectListFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.commons.injectfragment.HasInjectFragmentInterceptor

class ProjectListActivity : AppActivity(),
        ProjectListFragment.FragmentCallback,
        HasInjectFragmentInterceptor<ProjectListFragment> {

    // =============== HasInjectFragmentInterceptor ================================================

    override fun onLoadFragmentContainer(): View = mContentView.findViewById(R.id.content_view)

    override fun onCreateFragment(): ProjectListFragment = ProjectListFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: ProjectListFragment) {}
}