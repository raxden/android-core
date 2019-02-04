package com.core.app.injector.module

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerActivity
import com.core.app.injector.scope.PerFragment
import com.core.app.observer.BroadcastActivityObserver
import com.core.app.observer.TrackerViewObserver
import com.core.app.util.BroadcastOperationManager
import com.core.app.util.TrackerManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
object LifecycleActivityModule {

    @JvmStatic
    @Provides
    @IntoSet
    @PerActivity
    internal fun broadcastActivityObserver(activity: AppCompatActivity, broadcastOperationManager: BroadcastOperationManager): LifecycleObserver = BroadcastActivityObserver(activity, broadcastOperationManager)

}
