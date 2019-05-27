package com.core.app.lifecycle.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@PerActivity
class CompositeActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity,
        private val compositeDisposable: CompositeDisposable
) : BaseActivityLifecycleObserver(activity) {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        compositeDisposable.dispose()
    }
}