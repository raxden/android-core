package com.core.app.lifecycle

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.base.fragment.BaseFragmentModule
import com.core.app.injector.scope.PerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@PerFragment
class CompositeFragmentLifecycle @Inject internal constructor(
        fragment: Fragment,
        @field:Named(BaseFragmentModule.FRAGMENT_COMPOSITE_DISPOSABLE) private val compositeDisposable: CompositeDisposable
) : BaseFragmentLifecycleObserver(fragment) {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        compositeDisposable.dispose()
    }
}