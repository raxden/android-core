package com.core.app.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.runner.RunWith
import com.core.app.ui.screens.splash.SplashActivity
import com.core.app.ui.screens.splash.SplashViewModel
import com.core.app.util.ViewModelUtil
import com.core.domain.Forward
import com.core.domain.User
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(AndroidJUnit4::class)
class SplashTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SplashActivity::class.java, true, true)

    private lateinit var splashActivity: SplashActivity
    private lateinit var splashViewModel: SplashViewModel

    private val versionLiveData = MutableLiveData<String>()
    private val applicationReadyLiveData = MutableLiveData<Pair<Forward, User?>>()

    @Before
    fun init() {
        splashViewModel = Mockito.mock(SplashViewModel::class.java).also {
            `when`(it.version).thenReturn(versionLiveData)
            `when`(it.applicationReady).thenReturn(applicationReadyLiveData)
        }

        splashActivity = (activityRule.activity as SplashActivity).also {
            it.viewModelFactory = ViewModelUtil.createFor(splashViewModel)
        }
    }

    @Test
    fun anything() {
//        versionData.postValue("version 1.0")
//        onView(withId(R.id.version_name_view)).check(matches(ViewMatchers.isDisplayed()))
    }
}