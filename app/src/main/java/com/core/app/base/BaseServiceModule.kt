package com.core.app.base

import dagger.Module

/**
 * Provides base service dependencies. This must be included in all services modules, which must
 * provide a concrete implementation of [android.app.Service].
 */
@Module
abstract class BaseServiceModule {

    @Module
    companion object {

        const val DISPOSABLE_SERVICE_MANAGER = "BaseServiceModule.disposableServiceManager"

//        @JvmStatic
//        @Provides
//        @Named(DISPOSABLE_SERVICE_MANAGER)
//        @PerService
//        internal fun disposableServiceManager(): DisposableManager = DisposableManager()

    }

}
