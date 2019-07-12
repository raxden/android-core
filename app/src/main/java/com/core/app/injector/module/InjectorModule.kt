package com.core.app.injector.module

import com.core.app.injector.scope.PerActivity
import com.core.app.ui.screens.login.LoginActivity
import com.core.app.ui.screens.login.LoginActivityModule
import com.core.app.ui.screens.home.HomeActivity
import com.core.app.ui.screens.home.HomeActivityModule
import com.core.app.ui.screens.splash.SplashActivity
import com.core.app.ui.screens.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Provides application-wide dependencies.
 */
@Module
abstract class InjectorModule {

    // ============= Activities ====================================================================

    /**
     * Provides the injector for the [SplashActivity], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    internal abstract fun splashActivity(): SplashActivity

    /**
     * Provides the injector for the [LoginActivity], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(LoginActivityModule::class))
    internal abstract fun loginActivity(): LoginActivity

    /**
     * Provides the injector for the [HomeActivity], which has access to the dependencies
     * provided by this application instance (singleton scoped objects).
     */
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(HomeActivityModule::class))
    internal abstract fun projectListActivity(): HomeActivity
}
