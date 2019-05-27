package com.core.app.lifecycle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import javax.inject.Inject

@PerActivity
class ToolbarActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycleObserver(activity) {

    interface Callback {
        fun onCreateToolbarView(): Toolbar

        fun onToolbarViewCreated(toolbar: Toolbar)
    }

    private val callback: Callback
    private var mToolbar: Toolbar? = null

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        callback = activity as Callback
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        mToolbar = callback.onCreateToolbarView().also {
            activity.setSupportActionBar(it)
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)
            it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
            callback.onToolbarViewCreated(it)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mToolbar = null
    }
}