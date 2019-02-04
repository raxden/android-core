package com.core.app.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.core.app.helper.NavigationHelper
import com.core.app.util.BroadcastOperationManager
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.Interceptor
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Abstract Activity for all Activities to extend.
 */
abstract class BaseActivity : SquareActivity(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var mNavigationHelper: NavigationHelper
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable
    @Inject
    lateinit var mBroadcastOperationManager: BroadcastOperationManager
    @Inject
    lateinit var mLifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    lateinit var mFragmentManager: FragmentManager
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>

    // =============== LifeCycle ===================================================================
    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = mFragmentInjector

    // =============== Support methods =============================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) { }
}
