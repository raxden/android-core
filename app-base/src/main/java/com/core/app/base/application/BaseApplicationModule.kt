package com.core.app.base.application

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.core.app.injector.module.*
import com.core.data.network.NetworkModule
import com.core.data.persistence.PersistenceModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module(includes = [
    SchedulerModule::class,
    GsonModule::class,
    AnalyticsModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    PreferencesModule::class,
    NetworkModule::class,
    PersistenceModule::class
])
abstract class BaseApplicationModule {

    @Binds
    @Singleton
    internal abstract fun context(application: Application): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun contentResolver(application: Application): ContentResolver = application.contentResolver
    }
}
