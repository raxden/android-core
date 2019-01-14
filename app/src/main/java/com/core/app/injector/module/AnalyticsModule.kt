package com.core.app.injector.module

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AnalyticsModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun firebaseAnalytics(application: Application): FirebaseAnalytics {
        return FirebaseAnalytics.getInstance(application)
    }

}
