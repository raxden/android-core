package com.core.app.injector.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import com.core.app.injector.scope.PerActivity
import com.core.app.observer.BroadcastActivityObserver
import com.core.app.observer.CompositeActivityObserver
import com.core.app.util.BroadcastOperationManager
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import io.reactivex.disposables.CompositeDisposable

@Module
object LifecycleActivityModule {

    @JvmStatic
    @Provides
    @IntoSet
    @PerActivity
    internal fun compositeActivityObserver(activity: AppCompatActivity, disposable: CompositeDisposable): LifecycleObserver = CompositeActivityObserver(activity, disposable)

    @JvmStatic
    @Provides
    @IntoSet
    @PerActivity
    internal fun broadcastActivityObserver(activity: AppCompatActivity, broadcast: BroadcastOperationManager): LifecycleObserver = BroadcastActivityObserver(activity, broadcast)

}
