package com.core.app.ui.splash.view

import androidx.fragment.app.Fragment
import com.core.app.base.fragment.BaseFragmentModule
import com.core.app.base.fragment.BaseViewFragmentModule
import com.core.app.base.fragment.BaseViewModelFragmentModule
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = arrayOf(
        BaseViewModelFragmentModule::class
))
abstract class SplashFragmentModule {

    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment].
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: SplashFragment): Fragment
}
