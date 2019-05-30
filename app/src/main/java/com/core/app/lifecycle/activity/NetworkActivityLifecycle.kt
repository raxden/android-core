package com.core.app.lifecycle.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import com.core.app.util.BroadcastOperationManager
import com.core.commons.NetworkUtils
import javax.inject.Inject

@PerActivity
class NetworkActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface Callback {
        fun onWifiAvailable(available: Boolean)

        fun onNetworkAvailable(available: Boolean)
    }

    private val mNetworkBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            callback.onNetworkAvailable(NetworkUtils.isNetworkAvailable(context))
            callback.onWifiAvailable(NetworkUtils.isWifiAvailable(context))
        }
    }

    private val callback: Callback

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("UNCHECKED_CAST")
        callback = activity as Callback
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        activity.registerReceiver(mNetworkBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        activity.unregisterReceiver(mNetworkBroadcastReceiver)
    }
}