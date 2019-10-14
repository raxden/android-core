package com.core.app.base.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.fragment.TrackerFragmentLifecycle
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import javax.inject.Named

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module
abstract class BaseFragmentModule {

    @Binds
    @IntoSet
    @PerFragment
    @Named(LIFECYCLE_FRAGMENT_OBSERVER)
    internal abstract fun trackerLifecycleObserver(lifecycleObserver: TrackerFragmentLifecycle): LifecycleObserver

    @Module
    companion object {

        const val LIFECYCLE_FRAGMENT_OBSERVER = "LifecycleFragmentModule.fragment"
    }
}
