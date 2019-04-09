package com.core.app.base.activity

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.core.app.helper.NavigationHelper
import com.core.app.util.BroadcastOperationManager
import com.core.app.util.LocaleManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Abstract Activity for all Activities to extend.
 */
abstract class BaseActivity : AppCompatActivity(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var mLocaleManager: LocaleManager
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

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(mLocaleManager.updateBaseContextLocale(newBase))
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = mFragmentInjector
}
