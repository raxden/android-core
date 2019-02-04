package com.core.app.observer

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.util.TrackerManager
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

class TrackerViewObserver(
        private val mFragment: Fragment,
        private val mTrackerManager: TrackerManager
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        mFragment.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        mTrackerManager.trackScreen(mFragment)
    }
}