package com.core.app

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.jakewharton.threetenabp.AndroidThreeTen
import com.core.app.base.BaseApplication
import com.core.app.injector.component.DaggerApplicationComponent
import com.core.app.util.CrashReportingTree
import io.fabric.sdk.android.Fabric
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

/**
 * Created by Angel on 18/07/2017.
 */

class AppApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initRxPlugins()
        initFabric()
        initTimber()
        initTreeTen()
    }

    override fun initDaggerApplicationComponent() {
        DaggerApplicationComponent.builder().create(this).inject(this)
    }

    private fun initRxPlugins() {
        RxJavaPlugins.setErrorHandler { throwable: Throwable ->
            Timber.d(throwable)
        }
    }

    private fun initFabric() {
        // Initializes Fabric for builds that don't use the debug build type.
        Fabric.with(this, Crashlytics.Builder()
                .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
                .build())
    }

    private fun initTimber() {
        Timber.plant(when (BuildConfig.DEBUG) {
            true -> Timber.DebugTree()
            else -> CrashReportingTree()
        })
    }

    private fun initTreeTen() {
        // Initialize the timezone information
        AndroidThreeTen.init(this)
    }
}
