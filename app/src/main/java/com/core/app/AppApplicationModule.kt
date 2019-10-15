package com.core.app

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
abstract class AppApplicationModule {

    @Binds
    @Singleton
    internal abstract fun context(application: AppApplication): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        internal fun contentResolver(application: AppApplication): ContentResolver = application.contentResolver
    }
}
