package com.core.app.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.util.TrackerManager
import javax.inject.Inject

class TrackerFragmentLifecycle @Inject internal constructor(
        private val fragment: Fragment,
        private val trackerManager: TrackerManager
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        fragment.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        trackerManager.trackScreen(fragment)
    }
}