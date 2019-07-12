package com.core.app.util

import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

class TrackerManager(
        private val firebaseAnalytics: FirebaseAnalytics,
        private val screenNames: MutableMap<String, String>
) {

    fun trackScreen(fragment: Fragment) {
        screenNames[fragment.javaClass.name]?.takeIf { it.isNotEmpty() }.let {
            Timber.d("trackScreen: screenName[%s]", it)
            firebaseAnalytics.setCurrentScreen(fragment.activity!!, it, null)
        }
    }
}