package com.core.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.app.RxSchedulerRule
import com.core.app.ui.screens.home.HomeViewModel
import com.core.app.ui.screens.login.LoginViewModel
import com.core.domain.Project
import com.core.domain.User
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
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
class HomeViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @Mock
    lateinit var getProjectListUseCase: GetProjectListUseCase
    @Mock
    lateinit var logoutUseCase: LogoutUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        getProjectListUseCase.apply {
            Mockito.`when`(execute()).thenReturn(Maybe.just(mutableListOf<Project>().apply {
                add(Project(1))
                add(Project(2))
                add(Project(3))
            }))
        }
        logoutUseCase.apply {
            Mockito.`when`(execute()).thenReturn(Completable.complete())
        }
        viewModel = HomeViewModel(getProjectListUseCase, logoutUseCase)
    }

    @Test
    fun checkProjectList() {
        assert(viewModel.projectList.value?.isNotEmpty() == true)
    }

    @Test
    fun performLogout() {
        viewModel.performLogout()

        assert(viewModel.logoutCompleted.value?.getContentIfNotHandled() == true)
    }
}
