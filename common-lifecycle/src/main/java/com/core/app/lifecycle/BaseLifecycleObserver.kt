package com.core.app.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

abstract class BaseLifecycleObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("LeakingThis")
        lifecycle.addObserver(this)
    }
}