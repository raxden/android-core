package com.core.app.ui.splash

import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.app.R
import com.core.app.RxSchedulerRule
import com.core.app.ui.screens.splash.SplashActivity
import com.core.app.ui.screens.splash.view.SplashFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class Splash2Test {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private lateinit var mActivityController: ActivityController<SplashActivity>
    private lateinit var mSplashActivity: SplashActivity
    private lateinit var mSplashFragment: SplashFragment

    @Before
    fun setUp() {
        mActivityController = Robolectric.buildActivity(SplashActivity::class.java).apply {
            create().start().resume()
            mSplashActivity = get()
            mSplashActivity.getFragment()?.also {
                mSplashFragment = it
//                mLoginViewModel = mLoginFragment.viewModel
            }
        }
    }

    @Test
    fun checkAnything() {
        assertNotNull(mSplashFragment.view?.findViewById<TextView>(R.id.version_name_view))
    }
}