package com.core.app.ui.screens.project.list

import android.app.Activity
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.activity.BaseActivityModule
import com.core.app.base.activity.BaseFragmentActivityModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.InjectFragmentActivityLifecycle
import com.core.app.lifecycle.ToolbarActivityLifecycle
import com.core.app.ui.screens.project.list.view.ProjectListFragment
import com.core.app.ui.screens.project.list.view.ProjectListFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoSet

/**
 * Provides splash activity dependencies
 */
@Module(includes = [BaseFragmentActivityModule::class])
abstract class ProjectListActivityModule {

    /**
     * As per the contract specified in [BaseActivityModule]; "This must be included in all
     * activity modules, which must provide a concrete implementation of [Activity]."
     *
     *
     * This provides the activity required to inject the dependencies into the
     * [BaseActivity].
     *
     * @param activity the ProjectListActivity
     * @return the activity
     */
    @Binds
    @PerActivity
    internal abstract fun activity(activity: ProjectListActivity): Activity

    /**
     * The main activity listens to the events in the [ProjectListFragment].
     *
     * @param activity the activity
     * @return the main fragment callback
     */
    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: ProjectListActivity): ProjectListFragment.FragmentCallback

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun toolbarLifecycleObserver(lifecycleObserver: ToolbarActivityLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun injectFragmentLifecycleObserver(lifecycleObserver: InjectFragmentActivityLifecycle<ProjectListFragment>): LifecycleObserver

    // =============================================================================================

    /**
     * Provides the injector for the [ProjectListFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(ProjectListFragmentModule::class))
    internal abstract fun fragmentInjector(): ProjectListFragment
}
