package com.core.app.base.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.fragment.BaseFragmentModule.Companion.FRAGMENT_COMPOSITE_DISPOSABLE
import com.core.app.base.fragment.BaseFragmentModule.Companion.FRAGMENT_PERMISSION_MANAGER
import com.core.app.helper.AnimationHelper
import com.core.app.injector.module.LifecycleFragmentModule.Companion.LIFECYCLE_FRAGMENT_OBSERVER
import com.core.app.util.PermissionManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

/**
 * Abstract (Dialog)Fragment for all (Dialog)Fragments and child (Dialog)Fragments to extend.
 * This contains some boilerplate dependency injection code and activity [Context].
 *
 *
 * **WHY EXTEND DialogFragment?**
 * [DialogFragment]s are simple extensions of Fragments. DialogFragments can be shown as a
 * dialog floating above the current activity or be embedded into views like regular fragments.
 * Therefore, supporting both Fragments and DialogFragments for dependency injection can simply be
 * achieved by having the base fragment class (this) extend DialogFragment instead of Fragment.
 * We could have separate base classes for Fragments and DialogFragments but that would produce
 * duplicate code. See See https://github.com/vestrel00/android-dagger-butterknife-mvp/pull/64
 *
 *
 * Note that as of Dagger 2.12, the abstract base framework type
 * [dagger.android.DaggerDialogFragment] has been introduced for subclassing if so desired.
 *
 *
 * **DEPENDENCY INJECTION**
 * We could extend [dagger.android.DaggerDialogFragment] so we can get the boilerplate
 * dagger code for free. However, we want to avoid inheritance (if possible and it is in this case)
 * so that we have to option to inherit from something else later on if needed.
 *
 *
 * **VIEW BINDING**
 * This fragment handles view bind and unbinding.
 */
abstract class BaseFragment<TCallback: BaseFragment.BaseFragmentCallback> : Fragment(),
        HasSupportFragmentInjector {

    interface BaseFragmentCallback

    @Inject
    lateinit var animationHelper: AnimationHelper
    @Inject
    @field:Named(FRAGMENT_COMPOSITE_DISPOSABLE)
    lateinit var compositeDisposable: CompositeDisposable
    @Inject
    @field:Named(FRAGMENT_PERMISSION_MANAGER)
    lateinit var permissionManager: PermissionManager
    @Inject
    @field:Named(LIFECYCLE_FRAGMENT_OBSERVER)
    lateinit var lifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var callback: TCallback

    // =============== LifeCycle ===================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = childFragmentInjector
}
