package com.core.app.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.core.commons.DisposableManager
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.Interceptor
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Abstract Activity for all Activities to extend.
 */
abstract class BaseActivity : SquareActivity(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var mDisposableManager: DisposableManager
    @Inject
    lateinit var mFragmentManager: FragmentManager
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    // =============== LifeCycle ===================================================================
    override fun onDestroy() {
        mDisposableManager.removeAll()
        super.onDestroy()
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = mFragmentInjector

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) { }
}
