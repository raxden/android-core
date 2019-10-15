package com.core.app.lifecycle.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

class FullScreenActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        activity.window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
    }
}