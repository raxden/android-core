package com.core.app.lifecycle.activity

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.ViewPager
import com.core.app.injector.scope.PerActivity
import com.core.app.lifecycle.BaseActivityLifecycle
import javax.inject.Inject

@PerActivity
class FragmentStatePagerActivityLifecycle<TFragment : Fragment> @Inject internal constructor(
        activity: AppCompatActivity
) : BaseActivityLifecycle(activity) {

    interface Callback<TFragment> {
        val fragmentCount: Int

        fun onCreateViewPager(): ViewPager

        fun onViewPagerCreated(viewPager: ViewPager)

        fun onCreateFragment(position: Int): TFragment

        fun onPageTitle(position: Int): String

        fun onFragmentLoaded(position: Int, fragment: TFragment)

        fun onPageScrolled(position: Int)

        fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int)

        fun onPageSelected(position: Int)

        fun onPageScrollStateChanged(state: Int)
    }

    private val callback: Callback<TFragment>
    private lateinit var viewPager: ViewPager
    private var adapter: FragmentStatePagerInterceptorAdapter? = null

    val numPages: Int
        get() = adapter?.count ?: 0

    val currentPage: Int
        get() = viewPager.currentItem

    val isFirstPage: Boolean
        get() = currentPage == 0

    val isLastPage: Boolean
        get() = currentPage == numPages - 1

    init {
        // lifecycle-aware components, no need to unsubscribe/remove observers.
        @Suppress("UNCHECKED_CAST")
        callback = activity as Callback<TFragment>
    }

    fun setCurrentPage(page: Int) {
        viewPager.currentItem = page
    }

    fun setCurrentPage(page: Int, smoothScroll: Boolean) {
        viewPager.setCurrentItem(page, smoothScroll)
    }

    fun nextPage(): Boolean = if (isLastPage) false else {
        viewPager.currentItem = currentPage + 1
        true
    }

    fun previousPage(): Boolean = if (isFirstPage) false else {
        viewPager.currentItem = currentPage - 1
        true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate() {
        viewPager = callback.onCreateViewPager().let { viewPager ->
            (activity as? AppCompatActivity)?.let {
                adapter = FragmentStatePagerInterceptorAdapter(activity.supportFragmentManager)
                viewPager.adapter = adapter
                viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                        callback.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        if (positionOffset != 0.0f || positionOffsetPixels != 0) return
                        callback.onPageScrolled(position)
                    }

                    override fun onPageSelected(position: Int) {
                        callback.onPageSelected(position)
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                        callback.onPageScrollStateChanged(state)
                    }
                })
            }
            callback.onViewPagerCreated(viewPager)
            viewPager
        }
    }

    private inner class FragmentStatePagerInterceptorAdapter internal constructor(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

        override fun getItem(position: Int): TFragment {
            return callback.onCreateFragment(position)
        }

        override fun getCount(): Int = callback.fragmentCount

        override fun instantiateItem(container: ViewGroup, position: Int): TFragment {
            val fragment = super.instantiateItem(container, position) as TFragment
            callback.onFragmentLoaded(position, fragment)
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return callback.onPageTitle(position)
        }
    }
}