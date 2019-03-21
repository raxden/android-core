package com.core.app.lifecycle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

class CompositeActivityLifecycle(
        private val mActivity: AppCompatActivity,
        private val mCompositeDisposable: CompositeDisposable
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        mActivity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mCompositeDisposable.dispose()
    }
}