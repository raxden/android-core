package com.core.app.lifecycle.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.lifecycle.BaseActivityLifecycle
import com.core.app.util.BroadcastManager
import javax.inject.Inject

class BroadcastActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity,
        private val broadcastManager: BroadcastManager
) : BaseActivityLifecycle(activity) {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        broadcastManager.registerReceiver()
        (activity as? BroadcastManager.Listener)?.let { broadcastManager.addListener(it) }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        (activity as? BroadcastManager.Listener)?.let { broadcastManager.removeListener(it) }
        broadcastManager.unregisterReceiver()
    }
}