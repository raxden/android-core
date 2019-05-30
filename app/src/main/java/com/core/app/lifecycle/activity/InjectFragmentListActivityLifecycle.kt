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
class InjectFragmentListActivityLifecycle<TFragment : Fragment> @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface Callback<TFragment> {
        val fragmentCount: Int

        fun onLoadFragmentContainer(position: Int): View

        fun onCreateFragment(position: Int): TFragment

        fun onFragmentLoaded(position: Int, fragment: TFragment)
    }

    private val callback: Callback<TFragment>
    private var mContainerViewMap: MutableMap<Int, View> = mutableMapOf()
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("UNCHECKED_CAST")
        callback = activity as Callback<TFragment>
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        if (savedInstanceState == null) {
            for (position in 0 until callback.fragmentCount) {
                mContainerViewMap[position] = callback.onLoadFragmentContainer(position).also { view ->
                    mContainerFragmentMap[position] = callback.onCreateFragment(position).also {
                        activity.supportFragmentManager.beginTransaction()
                                .replace(view.id, it, it.javaClass.simpleName)
                                .commit()
                        callback.onFragmentLoaded(position, it)
                    }
                }
            }
        } else {
            for (position in 0 until callback.fragmentCount) {
                mContainerViewMap[position]?.also { view ->
                    mContainerFragmentMap[position] = (activity.supportFragmentManager.findFragmentById(view.id) as? TFragment)?.also {
                        callback.onFragmentLoaded(position, it)
                    }
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        mContainerViewMap.clear()
        mContainerFragmentMap.clear()
    }
}