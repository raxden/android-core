package com.core.app.injector.module

import android.app.Application
import com.core.app.ui.login.view.LoginFragment
import com.core.app.ui.project.list.view.ProjectListFragment
import com.core.app.ui.splash.view.SplashFragment
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
            Pair(SplashFragment::class.java.name, "splash"),
            Pair(LoginFragment::class.java.name, "login"),
            Pair(ProjectListFragment::class.java.name, "project_list"))

    @JvmStatic
    @Provides
    @Singleton
    internal fun trackerManager(
            firebaseAnalytics: FirebaseAnalytics,
            @Named("screensToTrack") screens: MutableMap<String, String>
    ): TrackerManager = TrackerManager(firebaseAnalytics, screens)
}
