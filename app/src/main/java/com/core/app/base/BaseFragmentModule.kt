package com.core.app.base

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.core.app.injector.module.InterceptorFragmentModule
import com.core.app.injector.scope.PerFragment
import com.core.commons.DisposableManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(
        includes = arrayOf(
                InterceptorFragmentModule::class
        )
)
abstract class BaseFragmentModule {

    @Module
    companion object {

        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"
        const val DISPOSABLE_FRAGMENT_MANAGER = "BaseFragmentModule.disposableManager"

        @JvmStatic
        @Provides
        @PerFragment
        internal fun dialogFragment(f: Fragment): DialogFragment = f as DialogFragment

        @JvmStatic
        @Provides
        @Named(DISPOSABLE_FRAGMENT_MANAGER)
        @PerFragment
        internal fun disposableManager(): DisposableManager = DisposableManager()

        @JvmStatic
        @Provides
        @Named(CHILD_FRAGMENT_MANAGER)
        @PerFragment
        internal fun fragmentManager(f: Fragment): FragmentManager = f.childFragmentManager
    }

}
