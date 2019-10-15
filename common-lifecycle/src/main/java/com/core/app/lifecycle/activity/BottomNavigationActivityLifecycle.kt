package com.core.app.lifecycle.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.core.app.lifecycle.BaseActivityLifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class BottomNavigationActivityLifecycle<TFragment : Fragment> @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface Callback<TFragment> {
        fun onCreateBottomNavigationView(): BottomNavigationView

        fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView)

        fun onLoadFragmentContainer(): View

        fun onCreateFragment(itemId: Int): TFragment

        fun onFragmentLoaded(itemId: Int, fragment: TFragment)

        fun onBottomNavigationItemSelected(itemId: Int)
    }

    private val callback: Callback<TFragment>
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mContainerView: View
    private var mContainerFragmentMap: MutableMap<Int, TFragment?> = mutableMapOf()

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("UNCHECKED_CAST")
        callback = activity as Callback<TFragment>
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        mContainerView = callback.onLoadFragmentContainer()
        mBottomNavigationView = initBottomNavigationView()

        if (savedInstanceState == null) {
            mBottomNavigationView.selectedItemId.also { id ->
                mContainerFragmentMap[id] = callback.onCreateFragment(id).also {
                    activity.supportFragmentManager.beginTransaction()
                            .replace(mContainerView.id, it, "fragment_$id")
                            .commit()
                    callback.onFragmentLoaded(id, it)
                }
            }
        } else {
            savedInstanceState?.getIntArray("itemIds")?.forEach { itemId ->
                mContainerFragmentMap[itemId] = (activity.supportFragmentManager.findFragmentByTag("fragment_$itemId") as? TFragment)?.also {
                    callback.onFragmentLoaded(itemId, it)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("selectedItemId", mBottomNavigationView.selectedItemId)
        outState.putIntArray("itemIds", mContainerFragmentMap.keys.toIntArray())
    }

    private fun initBottomNavigationView(): BottomNavigationView {
        return callback.onCreateBottomNavigationView().also { view ->
            savedInstanceState?.getInt("selectedItemId")?.also { id ->
                view.selectedItemId = id
            }
            view.setOnNavigationItemSelectedListener { menuItem ->
                mContainerFragmentMap[view.selectedItemId]?.also { activeFragment ->
                    val fragment = mContainerFragmentMap[menuItem.itemId]?.also {
                        activity.supportFragmentManager.beginTransaction()
                                .hide(activeFragment)
                                .show(it)
                                .commit()
                    } ?: callback.onCreateFragment(menuItem.itemId).also {
                        activity.supportFragmentManager.beginTransaction()
                                .add(mContainerView.id, it, "fragment_${menuItem.itemId}")
                                .hide(activeFragment)
                                .commit()
                    }
                    mContainerFragmentMap[menuItem.itemId] = fragment
                    callback.onFragmentLoaded(menuItem.itemId, fragment)
                }
                callback.onBottomNavigationItemSelected(menuItem.itemId)
                true
            }
            callback.onBottomNavigationViewCreated(view)
        }
    }
}