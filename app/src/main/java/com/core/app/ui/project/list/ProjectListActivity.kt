package com.core.app.ui.project.list

import android.os.Bundle
import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.ui.project.list.view.ProjectListFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import javax.inject.Inject

class ProjectListActivity : AppActivity(),
        ProjectListFragment.FragmentCallback,
        InjectFragmentInterceptorCallback<ProjectListFragment> {

    @Inject
    internal lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor

    // =============== InjectFragmentInterceptorCallback ===========================================

    override fun onCreateFragment(): ProjectListFragment? = ProjectListFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: ProjectListFragment) {}

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = mContentView.findViewById(R.id.content_view)

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mInjectFragmentInterceptor)
    }
}