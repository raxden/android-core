package com.core.app.lifecycle

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragmentLifecycle(val fragment: Fragment) : BaseLifecycleObserver(fragment.lifecycle) {

    protected var savedInstanceState: Bundle? = null

    fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
    }

    open fun onViewCreated() {}
}