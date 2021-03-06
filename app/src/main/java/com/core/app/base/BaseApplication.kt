package com.core.app.base

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.core.app.util.LocaleManager
import com.core.commons.AppUtils
import dagger.android.*
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseApplication : Application(),
        HasActivityInjector,
        HasServiceInjector,
        HasBroadcastReceiverInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>
    @Inject
    lateinit var broadcastReceiverDispatchingAndroidInjector: DispatchingAndroidInjector<BroadcastReceiver>

    // =============== LifeCycle ===================================================================

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.onConfigurationChanged(this)
    }

    override fun onCreate() {
        super.onCreate()

        initCompatVector()
        initDagger()
    }

    abstract fun initDaggerApplicationComponent()

    // =============== Injectors ===================================================================

    override fun activityInjector(): AndroidInjector<Activity>? = activityInjector

    override fun serviceInjector(): AndroidInjector<Service>? = serviceInjector

    override fun broadcastReceiverInjector(): AndroidInjector<BroadcastReceiver>? =
            broadcastReceiverDispatchingAndroidInjector

    // =============== Support methods =============================================================

    private fun initCompatVector() {
        if (!AppUtils.hasLollipop()) AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private fun initDagger() {
        initDaggerApplicationComponent()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is HasSupportFragmentInjector) AndroidInjection.inject(activity)
                (activity as? FragmentActivity)?.run {
                    supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
                            if (f is HasSupportFragmentInjector) AndroidSupportInjection.inject(f)
                        }
                    }, true)
                }
            }

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityStarted(activity: Activity) {}

            override fun onActivityDestroyed(activity: Activity) {}

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

            override fun onActivityStopped(activity: Activity) {}
        })
    }
}
