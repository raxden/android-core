package com.core.app.base

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import androidx.multidex.MultiDexApplication
import androidx.appcompat.app.AppCompatDelegate
import com.core.app.injector.AppInjector
import com.core.app.injector.component.DaggerApplicationComponent
import com.raxdenstudios.commons.util.SDKUtils
import dagger.android.*
import javax.inject.Inject

/**
 * The Android [MultiDexApplication].
 */
abstract class BaseApplication : MultiDexApplication(),
    HasActivityInjector,
    HasServiceInjector,
    HasBroadcastReceiverInjector {

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var mServiceInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var mBroadcastReceiverDispatchingAndroidInjector: DispatchingAndroidInjector<BroadcastReceiver>

    // =============== LifeCycle ===================================================================

    override fun onCreate() {
        super.onCreate()

        initCompatVector()
        initDependencyInjector()
    }

    // =============== Injectors ===================================================================

    override fun activityInjector(): AndroidInjector<Activity>? = mActivityInjector

    override fun serviceInjector(): AndroidInjector<Service>? = mServiceInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver>? =
        mBroadcastReceiverDispatchingAndroidInjector

    // =============== Support methods =============================================================

    private fun initCompatVector() {
        if (!SDKUtils.hasLollipop()) AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initDependencyInjector() {
        AppInjector.init(this)
    }

}
