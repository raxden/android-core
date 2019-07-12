package com.core.app.base.fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.core.app.helper.DialogHelper
import com.core.app.injector.module.LifecycleFragmentModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.util.PermissionManager
import com.core.app.util.TakePictureManager
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(includes = [LifecycleFragmentModule::class])
abstract class BaseFragmentModule {

    @Module
    companion object {

        const val FRAGMENT_COMPOSITE_DISPOSABLE = "BaseFragmentModule.compositeDisposable"
        const val FRAGMENT_PERMISSION_MANAGER = "BaseFragmentModule.permissionManager"
        const val FRAGMENT_TAKE_PICTURE_MANAGER = "BaseFragmentModule.takePictureManager"
        const val FRAGMENT_DIALOG_HELPER = "BaseFragmentModule.dialogHelper"

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
        internal fun permissionManager(
                activity: AppCompatActivity,
                rxPermissions: RxPermissions,
                @Named(FRAGMENT_COMPOSITE_DISPOSABLE) compositeDisposable: CompositeDisposable
        ): PermissionManager = PermissionManager(activity, rxPermissions, compositeDisposable)

        @JvmStatic
        @Provides
        @Named(FRAGMENT_TAKE_PICTURE_MANAGER)
        @PerFragment
        internal fun takePictureManager(
                fragment: Fragment,
                @Named(FRAGMENT_PERMISSION_MANAGER) permissionManager: PermissionManager
        ): TakePictureManager = TakePictureManager(fragment.childFragmentManager, permissionManager)

        @JvmStatic
        @Provides
        @Named(FRAGMENT_DIALOG_HELPER)
        @PerFragment
        internal fun dialogHelper(activity: AppCompatActivity): DialogHelper = DialogHelper(activity)
    }
}
