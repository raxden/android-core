package com.core.app.ui.screens.login.view

import com.core.app.ui.BaseTest
import com.core.app.ui.screens.login.LoginActivity
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class LoginTest : BaseTest() {

    private lateinit var mActivityController: ActivityController<LoginActivity>
    private lateinit var mLoginActivity: LoginActivity
    private lateinit var mLoginFragment: LoginFragment
    private lateinit var mLoginViewModel: LoginViewModel

    @Before
    fun setUp() {
        mActivityController = Robolectric.buildActivity(LoginActivity::class.java).apply {
            create().start().resume()
            mLoginActivity = get()
            mLoginActivity.mLoginFragment?.also {
                mLoginFragment = it
                mLoginViewModel = mLoginFragment.mViewModel
            }
        }
    }

    @Test
    fun check() {
        Assert.assertNotNull(mActivityController)
        Assert.assertNotNull(mLoginActivity)
        Assert.assertNotNull(mLoginFragment)
        Assert.assertNotNull(mLoginViewModel)
    }

    @Test
    fun checkEmptyLogin() {
        mLoginViewModel.onLoginClicked()
        Assert.assertNotEquals("", mLoginViewModel.usernameError.value)
        Assert.assertNotEquals("", mLoginViewModel.passwordError.value)
        Assert.assertNotEquals("", mLoginFragment.mBinding.usernameLayout.username.error)
        Assert.assertNotEquals("", mLoginFragment.mBinding.passwordLayout.password.error)
        Assert.assertEquals(null, mLoginViewModel.userLogged.value)
    }

    @Test
    fun checkLogin() {
        mLoginViewModel.username.postValue("username")
        mLoginViewModel.password.postValue("password")
        mLoginViewModel.onLoginClicked()
        Assert.assertEquals("", mLoginViewModel.usernameError.value)
        Assert.assertEquals("", mLoginViewModel.passwordError.value)
        Assert.assertNotNull(mLoginViewModel.userLogged.value)
    }
}
