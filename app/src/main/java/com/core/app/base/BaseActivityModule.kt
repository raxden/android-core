package com.core.app.base

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import com.core.app.helper.NavigationHelper
import com.core.app.injector.module.InterceptorActivityModule
import com.core.app.injector.module.ViewModelModule
import com.core.app.injector.scope.PerActivity
import com.core.app.util.ErrorManager
import com.core.app.util.LoaderManager
import com.core.commons.extension.getExtras
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module(
        includes = arrayOf(
                InterceptorActivityModule::class,
                ViewModelModule::class
        )
)
abstract class BaseActivityModule {

    @Binds
    @Named(ACTIVITY_CONTEXT)
    @PerActivity
    internal abstract fun context(activity: Activity): Context

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
        internal fun extras(activity: AppCompatActivity): Bundle? = activity.getExtras()

        @JvmStatic
        @Provides
        @PerActivity
        internal fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

        @JvmStatic
        @Provides
        @PerActivity
        internal fun fragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager

        @JvmStatic
        @Provides
        @PerActivity
        internal fun loaderManager(@Named(ACTIVITY_CONTEXT) context: Context): LoaderManager = LoaderManager(context)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun errorManager(@Named(ACTIVITY_CONTEXT) context: Context): ErrorManager = ErrorManager(context)

        @JvmStatic
        @Provides
        @PerActivity
        internal fun navigationHelper(activity: Activity): NavigationHelper = NavigationHelper(activity)
    }
}
