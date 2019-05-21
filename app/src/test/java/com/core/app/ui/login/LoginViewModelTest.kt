package com.core.app.ui.login

import android.text.TextUtils
import com.core.app.base.BaseTest
import com.core.app.ui.screens.login.LoginActivity
import com.core.app.ui.screens.login.view.LoginFragment
import com.core.app.ui.screens.login.view.LoginViewModel
import org.junit.Assert

import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

class LoginViewModelTest : BaseTest() {

    private lateinit var mActivityController: ActivityController<LoginActivity>
    private lateinit var mLoginActivity: LoginActivity
    private lateinit var mLoginFragment: LoginFragment
    private lateinit var mLoginViewModel: LoginViewModel

    @Before
    fun setUp() {
        mActivityController = Robolectric.buildActivity(LoginActivity::class.java).apply {
            create().start().resume()
            mLoginActivity = get()
            mLoginActivity.loginFragment?.also {
                mLoginFragment = it
                mLoginViewModel = mLoginFragment.viewModel
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
        mLoginViewModel.username.postValue("")
        mLoginViewModel.onLoginClicked()
        Assert.assertFalse(TextUtils.isEmpty(mLoginViewModel.usernameError.value))
        Assert.assertNull(mLoginViewModel.userLogged.value)
    }

    @Test
    fun checkSuccessLogin() {
        mLoginViewModel.username.postValue("username")
        mLoginViewModel.onLoginClicked()
        Assert.assertTrue(TextUtils.isEmpty(mLoginViewModel.usernameError.value))
        Assert.assertNotNull(mLoginViewModel.userLogged.value)
    }
}
