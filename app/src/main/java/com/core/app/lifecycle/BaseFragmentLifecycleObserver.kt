package com.core.app.lifecycle

import androidx.fragment.app.Fragment

abstract class BaseFragmentLifecycleObserver(val fragment: Fragment) : BaseLifecycleObserver(fragment.lifecycle)