package com.core.app.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.fragment.BaseFragmentModule.Companion.LIFECYCLE_FRAGMENT_OBSERVER
import com.core.app.helper.AnimationHelper
import com.core.app.helper.DialogHelper
import com.core.app.lifecycle.BaseFragmentLifecycle
import com.core.app.util.ErrorManager
import com.core.app.util.KeyboardManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
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
abstract class BaseFragment : DialogFragment(),
        HasSupportFragmentInjector {

    @Inject
    lateinit var dialogHelper: DialogHelper
    @Inject
    lateinit var animationHelper: AnimationHelper
    @Inject
    lateinit var keyboardManager: KeyboardManager
    @Inject
    lateinit var errorManager: ErrorManager
    @Inject
    @field:Named(LIFECYCLE_FRAGMENT_OBSERVER)
    lateinit var lifecycleObserverList: Set<@JvmSuppressWildcards LifecycleObserver>
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    // =============== LifeCycle ===================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        lifecycleObserverList.forEach { (it as? BaseFragmentLifecycle)?.onCreate(savedInstanceState) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleObserverList.forEach { (it as? BaseFragmentLifecycle)?.onViewCreated() }
    }

    // =============== HasFragmentInjector =========================================================

    override fun supportFragmentInjector() = childFragmentInjector
}
