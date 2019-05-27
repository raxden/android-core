package com.core.app.lifecycle.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.base.fragment.BaseFragmentModule
import com.core.app.injector.scope.PerFragment
import com.core.app.lifecycle.BaseFragmentLifecycle
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

@PerFragment
class CompositeFragmentLifecycle @Inject internal constructor(
        fragment: Fragment,
        @field:Named(BaseFragmentModule.FRAGMENT_COMPOSITE_DISPOSABLE) private val compositeDisposable: CompositeDisposable
) : BaseFragmentLifecycle(fragment) {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        compositeDisposable.dispose()
    }
}