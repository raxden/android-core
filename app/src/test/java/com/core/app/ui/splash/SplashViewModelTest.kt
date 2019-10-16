package com.core.app.ui.splash

import androidx.lifecycle.Observer
import com.core.app.CoroutinesTestRule
import com.core.app.ui.screens.splash.SplashViewModel
import com.core.commons.Resource
import com.core.domain.Forward
import com.core.domain.User
import com.core.domain.interactor.ForwardUseCase
import com.core.domain.interactor.GetVersionUseCase
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Mock
    private lateinit var getVersionUseCase: GetVersionUseCase
    @Mock
    private lateinit var forwardUseCase: ForwardUseCase
    @Mock
    private lateinit var versionObserver: Observer<String>
    @Mock
    private lateinit var applicationReadyObserver: Observer<Pair<Forward, User?>>
    @Mock
    private lateinit var throwableObserver: Observer<Throwable>

    private val forwardLogin = Pair<Forward, User?>(Forward.LOGIN, User())
    private val forwardHome = Pair<Forward, User?>(Forward.HOME, null)
    private val throwable = Throwable()

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {

    }

    @Test
    fun `check that application version is retrieved one time`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(getVersionUseCase.execute()).thenReturn(Resource.success("version_1"))
            `when`(forwardUseCase.execute()).thenReturn(Resource.success(forwardLogin))

            splashViewModel = SplashViewModel(getVersionUseCase, forwardUseCase).also {
                it.version.observeForever(versionObserver)
            }

            verify(versionObserver).onChanged("version_1")
        }
    }

    @Test
    fun `check that application is ready to launch login`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(getVersionUseCase.execute()).thenReturn(Resource.success("version_1"))
            `when`(forwardUseCase.execute()).thenReturn(Resource.success(forwardLogin))

            splashViewModel = SplashViewModel(getVersionUseCase, forwardUseCase).also {
                it.applicationReady.observeForever(applicationReadyObserver)
                it.throwable.observeForever(throwableObserver)
            }

            verify(applicationReadyObserver).onChanged(forwardLogin)
            verify(throwableObserver, never()).onChanged(throwable)
        }
    }

    @Test
    fun `check that application is ready to launch home`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(getVersionUseCase.execute()).thenReturn(Resource.success("version_1"))
            `when`(forwardUseCase.execute()).thenReturn(Resource.success(forwardHome))

            splashViewModel = SplashViewModel(getVersionUseCase, forwardUseCase).also {
                it.applicationReady.observeForever(applicationReadyObserver)
                it.throwable.observeForever(throwableObserver)
            }

            verify(applicationReadyObserver).onChanged(forwardHome)
            verify(throwableObserver, never()).onChanged(throwable)
        }
    }
}