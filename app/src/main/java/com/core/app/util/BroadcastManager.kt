package com.core.app.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.core.commons.AppUtils
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber
import java.util.*

class BroadcastManager(
        val activity: AppCompatActivity
) {

    interface Listener {
        fun onReceive(context: Context, intent: Intent)
    }

    private var observers: MutableList<Listener> = mutableListOf()

    fun send(code: Int) {
        activity.sendBroadcast(Intent(AppUtils.getPackageName(activity) + ".ACTIVITY_OPERATION").apply {
            putExtra("code", code)
        })
    }

    fun addListener(listener: Listener) {
        observers.add(listener)
    }

    fun removeListener(listener: Listener) {
        observers.remove(listener)
    }

    fun registerReceiver() {
        val intentFilter = IntentFilter(AppUtils.getPackageName(activity) + ".ACTIVITY_OPERATION")
        activity.registerReceiver(receiver, intentFilter)
    }

    fun unregisterReceiver() {
        activity.unregisterReceiver(receiver)
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            observers.forEach { it.onReceive(context, intent) }
        }
    }
}