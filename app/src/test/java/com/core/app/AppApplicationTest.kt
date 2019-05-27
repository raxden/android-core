package com.core.app

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.jakewharton.threetenabp.AndroidThreeTen
import com.core.app.base.BaseApplication
import com.core.app.injector.component.DaggerApplicationComponentTest
import com.core.app.util.CrashReportingTree
import com.raxdenstudios.square.InterceptorManager
import com.raxdenstudios.square.interceptor.commons.InterceptorCommonsFactory
import io.fabric.sdk.android.Fabric
import timber.log.Timber

/**
 * Created by Angel on 18/07/2017.
 */

class AppApplicationTest : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initFabric()
        initTimber()
        initTreeTen()
    }

    override fun initDaggerApplicationComponent() {
        DaggerApplicationComponentTest.builder().create(this).inject(this)
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
