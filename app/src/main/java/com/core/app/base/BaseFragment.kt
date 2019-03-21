package com.core.app.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.BaseFragmentModule.Companion.CHILD_FRAGMENT_MANAGER
import com.core.app.base.BaseFragmentModule.Companion.FRAGMENT_COMPOSITE_DISPOSABLE
import com.core.app.injector.module.LifecycleFragmentModule.LIFECYCLE_FRAGMENT_OBSERVER
import com.raxdenstudios.square.interceptor.Interceptor
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
    @field:Named(FRAGMENT_COMPOSITE_DISPOSABLE)
    lateinit var mCompositeDisposable: CompositeDisposable
    @Inject
    @field:Named(LIFECYCLE_FRAGMENT_OBSERVER)
    lateinit var mLifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    @field:Named(CHILD_FRAGMENT_MANAGER)
    lateinit var mChildFragmentManager: FragmentManager
    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var mCallback: TCallback

    // =============== LifeCycle ===================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = mChildFragmentInjector
}
