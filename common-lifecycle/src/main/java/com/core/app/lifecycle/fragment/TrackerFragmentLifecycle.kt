package com.core.app.lifecycle.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.lifecycle.BaseFragmentLifecycle
import com.core.app.util.TrackerManager
import javax.inject.Inject

class TrackerFragmentLifecycle @Inject internal constructor(
        fragment: Fragment,
        private val trackerManager: TrackerManager
) : BaseFragmentLifecycle(fragment) {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        trackerManager.trackScreen(fragment)
    }
}