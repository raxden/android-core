package com.core.app

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import com.core.app.AppApplication
import com.core.app.base.BaseApplicationModule
import com.core.app.injector.module.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module(includes = [
    BaseApplicationModule::class
])
abstract class AppApplicationTestModule {

    @Binds
    @Singleton
    internal abstract fun application(application: AppApplicationTest): Application
}
