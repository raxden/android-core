package com.core.app.base.fragment

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.core.app.injector.module.LifecycleFragmentModule
import com.core.app.injector.scope.PerFragment
import com.core.app.util.PermissionManager
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(
        includes = arrayOf(
                LifecycleFragmentModule::class
        )
)
abstract class BaseFragmentModule {

    @Module
    companion object {

        const val CHILD_FRAGMENT_MANAGER = "BaseFragmentModule.childFragmentManager"
        const val FRAGMENT_COMPOSITE_DISPOSABLE = "BaseFragmentModule.compositeDisposable"
        const val FRAGMENT_PERMISSION_MANAGER = "BaseFragmentModule.permissionManager"

        @JvmStatic
        @Provides
        @PerFragment
        internal fun dialogFragment(f: Fragment): DialogFragment = f as DialogFragment

        @JvmStatic
        @Provides
        @Named(FRAGMENT_COMPOSITE_DISPOSABLE)
        @PerFragment
        internal fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

        @JvmStatic
        @Provides
        @Named(FRAGMENT_PERMISSION_MANAGER)
        @PerFragment
        internal fun permissionManager(rxPermissions: RxPermissions, @Named(FRAGMENT_COMPOSITE_DISPOSABLE) compositeDisposable: CompositeDisposable): PermissionManager
                = PermissionManager(rxPermissions, compositeDisposable)

        @JvmStatic
        @Provides
        @Named(CHILD_FRAGMENT_MANAGER)
        @PerFragment
        internal fun childFragmentManager(f: Fragment): FragmentManager = f.childFragmentManager
    }
}
