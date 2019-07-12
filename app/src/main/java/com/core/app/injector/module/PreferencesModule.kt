package com.core.app.injector.module

import android.content.Context
import android.content.SharedPreferences
import com.core.commons.AppUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module
object PreferencesModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun advancedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(AppUtils.getPackageName(context), Context.MODE_PRIVATE)
    }
}
