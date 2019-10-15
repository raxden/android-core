package com.core.app.base.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.util.BroadcastManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.ErrorManager
import javax.inject.Inject

/**
 * Abstract Activity for all Activities to extend.
 */
abstract class BaseActivity : AppCompatActivity(),
        BroadcastManager.Listener,
        HasSupportFragmentInjector {

    protected abstract val layoutId: Int

    @Inject
    lateinit var animationHelper: AnimationHelper
    @Inject
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    lateinit var broadcastManager: BroadcastManager
    @Inject
    lateinit var errorManager: ErrorManager
    @Inject
    lateinit var permissionManager: PermissionManager
    @Inject
    lateinit var lifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    protected var rootView: View? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.attachBaseContext(newBase))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        lifecycleObserverList.forEach { (it as? BaseActivityLifecycle)?.onSaveInstanceState(outState) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onCreateView()?.also { onViewCreated(it) }

        lifecycleObserverList.forEach { (it as? BaseActivityLifecycle)?.onCreate(savedInstanceState) }
    }

    open fun onCreateView(): View? = when {
        layoutId != 0 -> {
            setContentView(layoutId)
            findViewById(android.R.id.content)
        }
        else -> null
    }

    open fun onViewCreated(view: View) {
        rootView = view
    }

    // =============== BroadcastManager.Listener ===================================================

    override fun onReceive(context: Context, intent: Intent) {}

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = fragmentInjector
}
