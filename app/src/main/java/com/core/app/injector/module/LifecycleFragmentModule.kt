package com.core.app.injector.module

import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.CompositeFragmentLifecycle
import com.core.app.lifecycle.TrackerFragmentLifecycle
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Named

@Module
abstract class LifecycleFragmentModule {

    @Module
    companion object {
        const val LIFECYCLE_FRAGMENT_OBSERVER = "LifecycleFragmentModule.fragment"
    }

    @Binds
    @IntoSet
    @PerFragment
    @Named(LIFECYCLE_FRAGMENT_OBSERVER)
    internal abstract fun compositeLifecycleObserver(lifecycleObserver: CompositeFragmentLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerFragment
    @Named(LIFECYCLE_FRAGMENT_OBSERVER)
    internal abstract fun trackerLifecycleObserver(lifecycleObserver: TrackerFragmentLifecycle): LifecycleObserver
}
