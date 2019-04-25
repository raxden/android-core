package com.core.app.ui.screens.login

import android.app.Activity
import com.core.app.base.activity.BaseActivityModule
import com.core.app.base.activity.BaseFragmentActivityModule
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.app.ui.screens.login.view.LoginFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Provides Login activity dependencies
 */
@Module(includes = [BaseFragmentActivityModule::class])
abstract class LoginActivityModule {

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
    internal abstract fun activity(activity: LoginActivity): Activity

    /**
     * The main activity listens to the events in the [LoginFragment].
     *
     * @param activity the activity
     * @return the main fragment mCallback
     */
    @Binds
    @PerActivity
    internal abstract fun fragmentCallback(activity: LoginActivity): LoginFragment.FragmentCallback

    // =============================================================================================

    /**
     * Provides the injector for the [LoginFragment], which has access to the dependencies
     * provided by this activity and application instance (singleton scoped objects).
     */
    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(LoginFragmentModule::class))
    internal abstract fun fragmentInjector(): LoginFragment
}
