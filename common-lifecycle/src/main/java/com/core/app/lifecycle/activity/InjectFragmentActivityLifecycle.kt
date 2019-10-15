package com.core.app.lifecycle.activity

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

@PerActivity
class InjectFragmentActivityLifecycle<TFragment : Fragment> @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface Callback<TFragment> {
        fun onLoadFragmentContainer(): View

        fun onCreateFragment(): TFragment

        fun onFragmentLoaded(fragment: TFragment)
    }

    private val callback: Callback<TFragment>
    private var fragment: TFragment? = null

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("UNCHECKED_CAST")
        callback = activity as Callback<TFragment>
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        fragment = callback.onLoadFragmentContainer().let { container ->
            if (savedInstanceState == null) {
                callback.onCreateFragment().also {
                    activity.supportFragmentManager.beginTransaction()
                            .replace(container.id, it, it.javaClass.simpleName)
                            .commit()
                    callback.onFragmentLoaded(it)
                }
            } else {
                (activity.supportFragmentManager.findFragmentById(container.id) as? TFragment)?.also {
                    callback.onFragmentLoaded(it)
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        fragment = null
    }
}