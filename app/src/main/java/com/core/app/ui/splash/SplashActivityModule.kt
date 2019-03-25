package com.core.app.ui.splash

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.BaseActivityModule
import com.core.app.base.BaseFragmentActivityModule
import com.core.app.injector.module.ViewModelModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.BroadcastActivityLifecycle
import com.core.app.lifecycle.FullScreenActivityLifecycle
import com.core.app.ui.splash.view.SplashFragment
import com.core.app.ui.splash.view.SplashFragmentModule
import com.core.app.util.BroadcastOperationManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet

/**
 * Provides splash activity dependencies
 */
@Module(includes = arrayOf(
        BaseFragmentActivityModule::class
))
abstract class SplashActivityModule {

    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [Activity]."
     *
     *
     * This provides the activity required to inject the dependencies into the
     * [BaseActivity].
     *
     * @param activity the RegisterActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    internal abstract fun activity(activity: SplashActivity): Activity

    /**
     * The main activity listens to the events in the [SplashFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: SplashActivity): SplashFragment.FragmentCallback

//    @Module
//    companion object {
//
//        @JvmStatic
//        @Provides
//        @IntoSet
//        @PerActivity
//        internal fun fullScreenActivityLifecycle(activity: AppCompatActivity): LifecycleObserver = FullScreenActivityLifecycle(activity)
//    }

    // =============================================================================================

    /**
     * Provides the injector for the [SplashFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(SplashFragmentModule::class))
    internal abstract fun fragmentInjector(): SplashFragment
}
