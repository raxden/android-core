package com.core.app.observer

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.util.BroadcastOperationManager

class BroadcastActivityObserver(
        private val mActivity: AppCompatActivity,
        private val mBroadcastOperationManager: BroadcastOperationManager
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        mActivity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        mBroadcastOperationManager.registerReceiver()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mBroadcastOperationManager.unregisterReceiver()
    }


}