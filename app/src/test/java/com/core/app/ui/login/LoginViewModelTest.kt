package com.core.app.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.core.app.RxSchedulerRule
import com.core.app.ui.screens.login.LoginViewModel
import com.core.commons.MockitoUtils
import com.core.domain.User
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
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
    @Mock
    private lateinit var versionObserver: Observer<String>
    @Mock
    private lateinit var userLoggedObserver: Observer<User>
    @Mock
    private lateinit var usernameErrorObserver: Observer<Int>
    @Mock
    private lateinit var throwableObserver: Observer<Throwable>

    private lateinit var loginViewModel: LoginViewModel

    private val user = User(username = "raxden")
    private val throwable = Throwable("user does not exist")

    @Before
    fun setUp() {
        `when`(getVersionUseCase.execute()).thenReturn(Single.just("version_1"))

        loginViewModel = LoginViewModel(getVersionUseCase, loginUseCase).also {
            it.version.observeForever(versionObserver)
            it.userLogged.observeForever(userLoggedObserver)
            it.usernameError.observeForever(usernameErrorObserver)
            it.throwable.observeForever(throwableObserver)
        }
    }

    @Test
    fun `check that application version is retrieved one time`() {
        verify(versionObserver).onChanged("version_1")
        verify(throwableObserver, never()).onChanged(throwable)
    }

    @Test
    fun `perform a success login`() {
        `when`(loginUseCase.execute("username")).thenReturn(Single.just(user))

        loginViewModel.username.value = "username"
        loginViewModel.performLogin()

        verify(userLoggedObserver).onChanged(user)
        verify(usernameErrorObserver, never()).onChanged(anyInt())
        verify(throwableObserver, never()).onChanged(throwable)
    }

    @Test
    fun `perform a fail login with empty username`() {
        loginViewModel.username.value = ""
        loginViewModel.performLogin()

        verify(userLoggedObserver, never()).onChanged(MockitoUtils.anyObject())
        verify(usernameErrorObserver).onChanged(anyInt())
        verify(throwableObserver, never()).onChanged(throwable)
    }

    @Test
    fun `perform a fail login with invalid username`() {
        `when`(loginUseCase.execute("user_not_exists")).thenReturn(Single.error(throwable))

        loginViewModel.username.value = "user_not_exists"
        loginViewModel.performLogin()

        verify(userLoggedObserver, never()).onChanged(MockitoUtils.anyObject())
        verify(usernameErrorObserver, never()).onChanged(anyInt())
        verify(throwableObserver).onChanged(throwable)
    }
}
