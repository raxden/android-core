package com.core.app.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.base.BaseFragmentModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class CompositeFragmentLifecycle @Inject internal constructor(
        private val mFragment: Fragment,
        @field:Named(BaseFragmentModule.FRAGMENT_COMPOSITE_DISPOSABLE) private val mCompositeDisposable: CompositeDisposable
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