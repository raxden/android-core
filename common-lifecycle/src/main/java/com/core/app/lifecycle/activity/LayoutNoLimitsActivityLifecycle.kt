package com.core.app.lifecycle.activity

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

class LayoutNoLimitsActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}