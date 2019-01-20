package com.core.app.base.mvvm

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.core.app.base.BaseFragmentModule
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(
        includes = arrayOf(
                BaseFragmentModule::class
        )
)
abstract class BaseViewFragmentModule {

    @Binds
    @PerFragment
    abstract fun viewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
