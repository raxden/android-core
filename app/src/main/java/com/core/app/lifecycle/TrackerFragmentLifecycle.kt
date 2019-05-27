package com.core.app.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerFragment
import com.core.app.util.TrackerManager
import javax.inject.Inject

@PerFragment
class TrackerFragmentLifecycle @Inject internal constructor(
        fragment: Fragment,
        private val trackerManager: TrackerManager
) : BaseFragmentLifecycleObserver(fragment) {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        trackerManager.trackScreen(fragment)
    }
}