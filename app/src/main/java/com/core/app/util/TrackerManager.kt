package com.core.app.util

import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

class TrackerManager(
        private val mFirebaseAnalytics: FirebaseAnalytics,
        private val mScreenNames: MutableMap<String, String>
) {

    fun trackScreen(fragment: Fragment) {
        mScreenNames[fragment.javaClass.name]?.takeIf { it.isNotEmpty() }.let {
            Timber.d("trackScreen: screenName[%s]", it)
            mFirebaseAnalytics.setCurrentScreen(fragment.activity!!, it, null)
        }
    }
}