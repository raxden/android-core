package com.core.app.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.app.RxSchedulerRule
import com.core.app.ui.screens.login.LoginViewModel
import com.core.domain.User
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@RunWith(RobolectricTestRunner::class)
class LoginViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var getVersionUseCase: GetVersionUseCase
    @Mock
    lateinit var loginUseCase: LoginUseCase

    private lateinit var viewModel: LoginViewModel

    private val user = User(username = "raxden")

    @Before
    fun setUp() {
        getVersionUseCase.apply {
            Mockito.`when`(execute()).thenReturn(Single.just("version_1"))
        }
        loginUseCase.apply {
            Mockito.`when`(execute("raxden")).thenReturn(Single.just(user))
            Mockito.`when`(execute("userNotExists")).thenReturn(Single.error(IllegalStateException()))
        }
        viewModel = LoginViewModel(getVersionUseCase, loginUseCase)
    }

    @Test
    fun checkApplicationVersion() {
        assertEquals("version_1", viewModel.version.value)
    }

    @Test
    fun performLogin() {
        viewModel.username.value = "raxden"
        viewModel.performLogin()

        assertEquals(user, viewModel.userLogged.value)
    }

    @Test
    fun performLoginWithEmptyUsername() {
        viewModel.username.value = ""
        viewModel.performLogin()

        assertNotNull(viewModel.usernameError.value)
    }

    @Test
    fun performLoginWithInvalidUsername() {
        viewModel.username.value = "userNotExists"
        viewModel.performLogin()

        assertNotNull(viewModel.throwable.value)
    }
}
