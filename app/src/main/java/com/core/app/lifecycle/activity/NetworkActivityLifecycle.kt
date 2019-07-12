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
import com.core.commons.NetworkUtils
import javax.inject.Inject

@PerActivity
class NetworkActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface OnNetworkChangeListener {
        fun onChange(available: Boolean)
    }

    interface OnWifiNetworkChangeListener {
        fun onChange(available: Boolean)
    }

    private val networkBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (currentNetworkStatus != NetworkUtils.isNetworkAvailable(context)) {
                currentNetworkStatus = !currentNetworkStatus
                networkChangeListenerList.forEach { it.onChange(currentNetworkStatus) }
            }
            if (currentWifiStatus != NetworkUtils.isWifiAvailable(context)) {
                currentWifiStatus = !currentWifiStatus
                wifiNetworkChangeListenerList.forEach { it.onChange(currentWifiStatus) }
            }
        }
    }

    private var currentNetworkStatus = false
    private var currentWifiStatus = false
    private val networkChangeListenerList: MutableList<OnNetworkChangeListener> = mutableListOf()
    private val wifiNetworkChangeListenerList: MutableList<OnWifiNetworkChangeListener> = mutableListOf()

    init {
        currentNetworkStatus = NetworkUtils.isNetworkAvailable(activity)
        currentWifiStatus = NetworkUtils.isWifiAvailable(activity)
    }

    fun addOnNetworkChangeListener(listener: OnNetworkChangeListener) {
        networkChangeListenerList.add(listener)
    }

    fun addOnWifiNetworkChangeListener(listener: OnWifiNetworkChangeListener) {
        wifiNetworkChangeListenerList.add(listener)
    }

    fun removeOnNetworkChangeListener(listener: OnNetworkChangeListener) {
        networkChangeListenerList.remove(listener)
    }

    fun removeOnWifiNetworkChangeListener(listener: OnWifiNetworkChangeListener) {
        wifiNetworkChangeListenerList.remove(listener)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        activity.registerReceiver(networkBroadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        networkChangeListenerList.clear()
        wifiNetworkChangeListenerList.clear()
        activity.unregisterReceiver(networkBroadcastReceiver)
    }
}