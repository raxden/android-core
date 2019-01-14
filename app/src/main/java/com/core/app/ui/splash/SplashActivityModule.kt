package com.core.app.ui.splash

import android.app.Activity
import com.core.app.base.BaseActivityModule
import com.core.app.base.BaseFragmentActivityModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

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

    // =============================================================================================

    /**
     * Provides the injector for the [SplashFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(SplashFragmentModule::class))
    internal abstract fun fragmentInjector(): SplashFragment

}
