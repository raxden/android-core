package com.core.app.injector.module

import android.app.Application
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.app.ui.screens.project.list.view.ProjectListFragment
import com.core.app.ui.screens.splash.view.SplashFragment
import com.core.app.util.TrackerManager
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AnalyticsModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun firebaseAnalytics(application: Application): FirebaseAnalytics =
            FirebaseAnalytics.getInstance(application)

    @JvmStatic
    @Provides
    @Singleton
    @Named("screensToTrack")
    internal fun screensToTrack(): MutableMap<String, String> = mutableMapOf(
            Pair(SplashFragment::class.java.name, ""),
            Pair(LoginFragment::class.java.name, ""),
            Pair(ProjectListFragment::class.java.name, "project_list"))

    @JvmStatic
    @Provides
    @Singleton
    internal fun trackerManager(
            firebaseAnalytics: FirebaseAnalytics,
            @Named("screensToTrack") screens: MutableMap<String, String>
    ): TrackerManager = TrackerManager(firebaseAnalytics, screens)
}
