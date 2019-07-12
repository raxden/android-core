package com.core.app.injector.module

import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.activity.BroadcastActivityLifecycle
import com.core.app.lifecycle.activity.CompositeActivityLifecycle
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class LifecycleActivityModule {

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun compositeLifecycleObserver(lifecycleObserver: CompositeActivityLifecycle): LifecycleObserver

    @Binds
    @IntoSet
    @PerActivity
    internal abstract fun broadcastLifecycleObserver(lifecycleObserver: BroadcastActivityLifecycle): LifecycleObserver
}
