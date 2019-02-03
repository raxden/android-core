package com.core.app.injector.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerFragment
import com.core.app.observer.TrackerViewObserver
import com.core.app.util.TrackerManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object LifecycleFragmentModule {

    @JvmStatic
    @Provides
    @IntoSet
    @PerFragment
    internal fun trackerViewObserver(f: Fragment, trackerManager: TrackerManager): LifecycleObserver = TrackerViewObserver(f, trackerManager)

}
