package com.core.app.ui.screens.home

import android.app.Activity
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.activity.BaseActivityModule
import com.core.app.base.activity.BaseFragmentActivityModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.lifecycle.activity.ToolbarActivityLifecycle
import com.core.app.ui.screens.home.list.HomeProjectListFragment
import com.core.app.ui.screens.home.list.HomeProjectListFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet

/**
 * Provides splash activity dependencies
 */
@Module(includes = [BaseFragmentActivityModule::class])
abstract class HomeActivityModule {

    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [Activity]."
     *
     *
     * This provides the activity required to inject the dependencies into the
     * [BaseActivity].
     *
     * @param activity the HomeActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    internal abstract fun activity(activity: HomeActivity): Activity

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun toolbarLifecycleObserver(lifecycleObserver: ToolbarActivityLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun injectFragmentLifecycleObserver(lifecycleObserver: InjectFragmentActivityLifecycle<HomeProjectListFragment>): LifecycleObserver

    // =============================================================================================

    /**
     * Provides the injector for the [HomeProjectListFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(HomeProjectListFragmentModule::class))
    internal abstract fun fragmentInjector(): HomeProjectListFragment
}
