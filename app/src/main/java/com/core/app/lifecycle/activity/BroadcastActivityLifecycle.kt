package com.core.app.lifecycle.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import com.core.app.util.BroadcastOperationManager
import javax.inject.Inject

@PerActivity
class BroadcastActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity,
        private val broadcastOperationManager: BroadcastOperationManager
) : BaseActivityLifecycle(activity) {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        broadcastOperationManager.registerReceiver()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        broadcastOperationManager.unregisterReceiver()
    }
}