package com.core.app.ui.login

import android.os.Bundle
import android.view.View
import com.core.app.AppActivity
import com.core.app.R
import com.core.app.ui.login.view.LoginFragment
import com.core.commons.extension.getExtras
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptor
import com.raxdenstudios.square.interceptor.commons.injectfragment.InjectFragmentInterceptorCallback
import javax.inject.Inject

class LoginActivity : AppActivity(),
        LoginFragment.FragmentCallback,
        InjectFragmentInterceptorCallback<LoginFragment> {

    @Inject
    internal lateinit var mInjectFragmentInterceptor: InjectFragmentInterceptor

    // =============== InjectFragmentInterceptorCallback ===========================================

    override fun onCreateFragment(): LoginFragment? = LoginFragment.newInstance(getExtras())

    override fun onFragmentLoaded(fragment: LoginFragment) {}

    override fun onLoadFragmentContainer(savedInstanceState: Bundle?): View = mContentView.findViewById(R.id.content_view)
    // =============== LoginFragment.FragmentCallback =============================================

    override fun onUserLogged() {
        mNavigationHelper.launchProjectListAndFinishCurrentView()
    }

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        super.setupInterceptors(interceptorList)
        interceptorList.add(mInjectFragmentInterceptor)
    }
}
