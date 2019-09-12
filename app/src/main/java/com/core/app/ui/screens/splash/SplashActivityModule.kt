package com.core.app.ui.screens.splash

import android.app.Activity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.core.app.base.activity.BaseActivityModule
import com.core.app.base.activity.BaseFragmentActivityModule
import com.core.app.base.activity.BaseViewModelFragmentActivityModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.activity.FullScreenActivityLifecycle
import com.core.app.lifecycle.activity.InjectFragmentActivityLifecycle
import com.core.app.ui.screens.splash.view.SplashFragment
import com.core.app.ui.screens.splash.view.SplashFragmentModule
import com.core.app.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

/**
 * Provides splash activity dependencies
 */
@Module(includes = [BaseViewModelFragmentActivityModule::class])
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

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun fullScreenLifecycleObserver(lifecycleObserver: FullScreenActivityLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun injectFragmentLifecycleObserver(lifecycleObserver: InjectFragmentActivityLifecycle<SplashFragment>): LifecycleObserver

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    // =============================================================================================

    /**
     * Provides the injector for the [SplashFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(SplashFragmentModule::class))
    internal abstract fun fragmentInjector(): SplashFragment
}
