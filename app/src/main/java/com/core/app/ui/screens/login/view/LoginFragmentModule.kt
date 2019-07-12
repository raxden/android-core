package com.core.app.ui.screens.login.view

import androidx.fragment.app.Fragment
import com.core.app.base.fragment.BaseFragmentModule
import com.core.app.base.fragment.BaseViewModelFragmentModule
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = [BaseViewModelFragmentModule::class])
abstract class LoginFragmentModule {

    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment].
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: LoginFragment): Fragment
}
