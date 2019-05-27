package com.core.app.lifecycle.fragment

import androidx.fragment.app.Fragment
import com.core.app.lifecycle.BaseLifecycleObserver

abstract class BaseFragmentLifecycleObserver(val fragment: Fragment) : BaseLifecycleObserver(fragment.lifecycle)