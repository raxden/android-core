package com.core.app.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith
import com.core.app.R
import com.core.app.ui.screens.splash.SplashActivity
import com.core.app.ui.screens.splash.SplashViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/*
* Migrate to Robolectric (test folder)
* */
@RunWith(AndroidJUnit4::class)
class SplashTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SplashActivity::class.java, true, true)

    private lateinit var activity: SplashActivity
    private lateinit var viewModel: SplashViewModel

    private val versionData = MutableLiveData<String>()

    @Before
    fun init() {
        viewModel = Mockito.mock(SplashViewModel::class.java)
        Mockito.`when`(viewModel.version).thenReturn(versionData)

        activity = activityRule.activity as SplashActivity
    }

    @Test
    fun anything() {
        versionData.postValue("version 1.0")
        onView(withId(R.id.version_name_view)).check(matches(ViewMatchers.isDisplayed()))
    }
}