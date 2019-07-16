package com.core.app.ui.login

import com.core.app.BaseTest
import com.core.app.ui.screens.login.LoginViewModel
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock

class LoginViewModelTest : BaseTest() {

    @Mock
    lateinit var getVersionUseCase: GetVersionUseCase
    @Mock
    lateinit var loginUseCase: LoginUseCase

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {

    }

//    private lateinit var mActivityController: ActivityController<LoginActivity>
//    private lateinit var mLoginActivity: LoginActivity
//    private lateinit var mLoginFragment: LoginFragment
//    private lateinit var mLoginViewModel: LoginViewModel
//
//    @Before
//    fun setUp() {
//        mActivityController = Robolectric.buildActivity(LoginActivity::class.java).apply {
//            create().start().resume()
//            mLoginActivity = get()
//            mLoginActivity.loginFragment?.also {
//                mLoginFragment = it
//                mLoginViewModel = mLoginFragment.viewModel
//            }
//        }
//    }
//
//    @Test
//    fun check() {
//        Assert.assertNotNull(mActivityController)
//        Assert.assertNotNull(mLoginActivity)
//        Assert.assertNotNull(mLoginFragment)
//        Assert.assertNotNull(mLoginViewModel)
//    }
//
//    @Test
//    fun checkEmptyLogin() {
//        mLoginViewModel.username.postValue("")
//        mLoginViewModel.onLoginClicked()
//        Assert.assertFalse(TextUtils.isEmpty(mLoginViewModel.usernameError.value))
//        Assert.assertNull(mLoginViewModel.userLogged.value)
//    }
//
//    @Test
//    fun checkSuccessLogin() {
//        mLoginViewModel.username.postValue("username")
//        mLoginViewModel.onLoginClicked()
//        Assert.assertTrue(TextUtils.isEmpty(mLoginViewModel.usernameError.value))
//        Assert.assertNotNull(mLoginViewModel.userLogged.value)
//    }
}
