package com.core.app.lifecycle

import androidx.fragment.app.Fragment
import com.core.app.lifecycle.BaseLifecycleObserver

abstract class BaseFragmentLifecycle(val fragment: Fragment) : BaseLifecycleObserver(fragment.lifecycle)