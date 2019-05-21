package com.core.app.lifecycle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.util.BroadcastOperationManager
import javax.inject.Inject

class BroadcastActivityLifecycle @Inject internal constructor(
        private val activity: AppCompatActivity,
        private val broadcastOperationManager: BroadcastOperationManager
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        broadcastOperationManager.registerReceiver()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        broadcastOperationManager.unregisterReceiver()
    }
}