package com.core.app.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivityLifecycle(val activity: AppCompatActivity) : BaseLifecycleObserver(activity.lifecycle) {

    var savedInstanceState: Bundle? = null

    fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }
}