package com.core.app.base.activity

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.core.app.BuildConfig
import com.core.app.helper.AnimationHelper
import com.core.app.helper.DialogHelper
import com.core.app.helper.NavigationHelper
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.activity.BroadcastActivityLifecycle
import com.core.app.lifecycle.activity.CompositeActivityLifecycle
import com.core.app.util.*
import com.tbruyelle.rxpermissions2.RxPermissions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module
abstract class BaseActivityModule {

    @Binds
    @Named(ACTIVITY_CONTEXT)
    @PerActivity
    internal abstract fun context(activity: Activity): Context

    @Binds
    @PerActivity
    internal abstract fun viewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun compositeLifecycleObserver(lifecycleObserver: CompositeActivityLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun broadcastLifecycleObserver(lifecycleObserver: BroadcastActivityLifecycle): LifecycleObserver

    @Module
    companion object {

        const val ACTIVITY_CONTEXT = "BaseActivityModule.activityContext"

        @JvmStatic
        @Provides
        @PerActivity
        internal fun activity(activity: Activity): AppCompatActivity = activity as AppCompatActivity

        @JvmStatic
        @Provides
        @PerActivity
        internal fun resources(activity: AppCompatActivity): Resources = activity.resources

        @JvmStatic
        @Provides
        @PerActivity
        internal fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

        @JvmStatic
        @Provides
        @PerActivity
        internal fun rxPermissions(
                activity: AppCompatActivity
        ): RxPermissions = RxPermissions(activity).apply { setLogging(BuildConfig.DEBUG) }

        @JvmStatic
        @Provides
        @PerActivity
        internal fun permissionManager(
                activity: AppCompatActivity,
                rxPermissions: RxPermissions,
                compositeDisposable: CompositeDisposable
        ): PermissionManager = PermissionManager(activity, rxPermissions, compositeDisposable)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun errorManager(activity: AppCompatActivity): ErrorManager = ErrorManager(activity)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun broadcastManager(activity: AppCompatActivity): BroadcastManager = BroadcastManager(activity)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun keyboardManager(activity: AppCompatActivity): KeyboardManager = KeyboardManager(activity)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun takePictureManager(
                activity: AppCompatActivity,
                permissionManager: PermissionManager
        ): TakePictureManager = TakePictureManager(activity.supportFragmentManager, permissionManager)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun navigationHelper(activity: AppCompatActivity): NavigationHelper = NavigationHelper(activity)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun dialogHelper(activity: AppCompatActivity): DialogHelper = DialogHelper(activity)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun animationHelper(activity: AppCompatActivity): AnimationHelper = AnimationHelper(activity)
    }
}
