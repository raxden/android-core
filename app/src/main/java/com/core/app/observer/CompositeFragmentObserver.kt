package com.core.app.observer

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable

class CompositeFragmentObserver(
        private val mFragment: Fragment,
        private val mCompositeDisposable: CompositeDisposable
) : LifecycleObserver {

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        mFragment.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mCompositeDisposable.dispose()
    }


}