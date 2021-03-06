package com.core.app.base

import com.core.app.injector.scope.PerService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

/**
 * Provides base service dependencies. This must be included in all services modules, which must
 * provide a concrete implementation of [android.app.Service].
 */
@Module
abstract class BaseServiceModule {

    @Module
    companion object {

        const val DISPOSABLE_SERVICE_MANAGER = "BaseServiceModule.disposableServiceManager"

        @JvmStatic
        @Provides
        @Named(DISPOSABLE_SERVICE_MANAGER)
        @PerService
        internal fun disposableServiceManager(): CompositeDisposable = CompositeDisposable()
    }
}
