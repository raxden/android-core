package com.core.app.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.core.app.R
import org.junit.runner.RunWith
import com.core.app.ui.screens.splash.SplashActivity
import com.core.app.ui.screens.splash.SplashViewModel
import com.core.app.util.ViewModelUtil
import com.core.domain.Forward
import com.core.domain.User
import net.bytebuddy.implementation.bytecode.Throw
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class SplashViewTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SplashActivity::class.java, true, true)

    private lateinit var splashActivity: SplashActivity
    private lateinit var splashViewModel: SplashViewModel

    private val versionLiveData = MutableLiveData<String>()
    private val applicationReadyLiveData = MutableLiveData<Pair<Forward, User?>>()
    private val throwable = MutableLiveData<Throwable>()

    @Before
    fun init() {
        splashViewModel = Mockito.mock(SplashViewModel::class.java).also {
            `when`(it.version).thenReturn(versionLiveData)
            `when`(it.applicationReady).thenReturn(applicationReadyLiveData)
        }

        splashActivity = (activityRule.activity as SplashActivity).also {
            it.viewModelFactory = ViewModelUtil.createFor(splashViewModel)
        }
        val fragment = splashActivity.getFragment()
        fragment?.also {
            it.viewModelFactory = ViewModelUtil.createFor(splashViewModel)
        }
    }

    @Test
    fun anything() {
        versionLiveData.postValue("version 1.0")
        onView(withId(R.id.version_name_view)).check(matches(ViewMatchers.isDisplayed()))
    }
}