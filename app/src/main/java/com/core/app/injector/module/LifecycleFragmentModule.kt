package com.core.app.injector.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.base.BaseFragmentModule.Companion.FRAGMENT_COMPOSITE_DISPOSABLE
import com.core.app.injector.scope.PerFragment
import com.core.app.observer.CompositeFragmentObserver
import com.core.app.observer.TrackerFragmentObserver
import com.core.app.util.TrackerManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

@Module
object LifecycleFragmentModule {

    const val LIFECYCLE_FRAGMENT_OBSERVER = "LifecycleFragmentModule.fragment"

    @JvmStatic
    @Provides
    @IntoSet
    @PerFragment
    @Named(LIFECYCLE_FRAGMENT_OBSERVER)
    internal fun compositeFragmentObserver(f: Fragment, @Named(FRAGMENT_COMPOSITE_DISPOSABLE) disposable: CompositeDisposable): LifecycleObserver = CompositeFragmentObserver(f, disposable)

    @JvmStatic
    @Provides
    @IntoSet
    @PerFragment
    @Named(LIFECYCLE_FRAGMENT_OBSERVER)
    internal fun trackerViewObserver(f: Fragment, manager: TrackerManager): LifecycleObserver = TrackerFragmentObserver(f, manager)

}
