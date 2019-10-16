package com.core.app.injector.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module
object SchedulerModule {

    @JvmStatic
    @Provides
    @Singleton
    @Named("io")
    internal fun io(): CoroutineDispatcher = Dispatchers.IO

    @JvmStatic
    @Provides
    @Singleton
    @Named("main")
    internal fun main(): CoroutineDispatcher = Dispatchers.Main

    @JvmStatic
    @Provides
    @Singleton
    @Named("default")
    internal fun default(): CoroutineDispatcher = Dispatchers.Default

    @JvmStatic
    @Provides
    @Singleton
    @Named("unconfined")
    internal fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}
