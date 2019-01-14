package com.core.app.base.mvvm

import com.core.app.injector.scope.PerFragment
import com.core.commons.DisposableManager
import com.core.commons.LoaderManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Provides base viewModel dependencies. This must be included in all viewModel modules
 */
@Module
abstract class BaseViewModelModule {

    @Module
    companion object {

        const val DISPOSABLE_VIEWMODEL_MANAGER = "BaseViewModelModule.disposableManager"

        @JvmStatic
        @Provides
        @PerFragment
        internal fun loaderManager(): LoaderManager = LoaderManager()

        @JvmStatic
        @Provides
        @Named(DISPOSABLE_VIEWMODEL_MANAGER)
        @PerFragment
        internal fun disposableManager(): DisposableManager = DisposableManager()
    }

}
