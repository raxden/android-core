package com.core.app.injector.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    internal fun io(): Scheduler = Schedulers.io()

    @JvmStatic
    @Provides
    @Singleton
    @Named("mainThread")
    internal fun mainThread(): Scheduler =  AndroidSchedulers.mainThread()
}
