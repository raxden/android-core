package com.core.app.lifecycle.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

@PerActivity
open class ToolbarActivityLifecycle @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

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
//            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.setDisplayShowTitleEnabled(false)
//            it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) } // comment this line to expand onOptionsItemSelected on fragments
            it.setNavigationOnClickListener { activity.onBackPressed() }
            callback.onToolbarViewCreated(it)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mToolbar = null
    }
}