package com.core.app.base.activity

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.core.app.helper.AnimationHelper
import com.core.app.helper.NavigationHelper
import com.core.app.lifecycle.BaseActivityLifecycle
import com.core.app.util.BroadcastOperationManager
import com.core.app.util.LocaleManager
import com.core.app.util.PermissionManager
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
    lateinit var navigationHelper: NavigationHelper
    @Inject
    lateinit var animationHelper: AnimationHelper
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var broadcastOperationManager: BroadcastOperationManager
    @Inject
    lateinit var permissionManager: PermissionManager
    @Inject
    lateinit var lifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    lateinit var fragmentManager: FragmentManager
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.attachBaseContext(newBase))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        lifecycleObserverList.forEach { (it as? BaseActivityLifecycle)?.onSaveInstanceState(outState) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleObserverList.forEach { (it as? BaseActivityLifecycle)?.onCreate(savedInstanceState) }
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = fragmentInjector
}
