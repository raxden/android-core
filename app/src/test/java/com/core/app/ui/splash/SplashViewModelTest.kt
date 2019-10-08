package com.core.app.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.core.app.RxSchedulerRule
import com.core.app.ui.screens.splash.SplashViewModel
import com.core.domain.interactor.ForwardUseCase
import com.core.domain.interactor.GetVersionUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SplashViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    private lateinit var getVersionUseCase: GetVersionUseCase
    @Mock
    private lateinit var forwardUseCase: ForwardUseCase
    @Mock
    private lateinit var versionObserver: Observer<String>

    private lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        splashViewModel = SplashViewModel(getVersionUseCase, forwardUseCase)
    }

    @Test
    fun `check that application version is retrieved one time`() {
        `when`(getVersionUseCase.execute()).thenReturn(Single.just("version_1"))

        splashViewModel.version.observeForever(versionObserver)
        inOrder(versionObserver).apply {
            verify(versionObserver, times(1)).onChanged("version_1")
        }
    }
}