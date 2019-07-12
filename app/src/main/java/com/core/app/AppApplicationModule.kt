package com.core.app

import android.app.Application
import com.core.app.base.BaseApplicationModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module(includes = [BaseApplicationModule::class])
abstract class AppApplicationModule {

    @Binds
    @Singleton
    internal abstract fun application(application: AppApplication): Application
}
