package com.core.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.core.app.RxSchedulerRule
import com.core.app.model.ProjectModel
import com.core.app.ui.screens.home.HomeViewModel
import com.core.domain.Project
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner

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

    @Mock
    lateinit var statusObserver: Observer<Boolean>
    @Mock
    lateinit var projectListObserver: Observer<List<ProjectModel>>

    private lateinit var viewModel: HomeViewModel

    private val projectListData = listOf(
            Project(1),
            Project(2),
            Project(3)
    )

    @Before
    fun setUp() {
        getProjectListUseCase.apply {
            Mockito.`when`(execute()).thenReturn(Maybe.just(projectListData))
        }
        logoutUseCase.apply {
            Mockito.`when`(execute()).thenReturn(Completable.complete())
        }
        viewModel = HomeViewModel(getProjectListUseCase, logoutUseCase)
    }

    @Test
    fun `retrieve project list data`() {
        viewModel.loader.status.observeForever(statusObserver)
        viewModel.projectModelList.observeForever(projectListObserver)
        viewModel.retrieveProjectList()

        Mockito.inOrder(statusObserver).apply {
            verify(statusObserver).onChanged(true)
            verify(statusObserver).onChanged(false)
        }
        assert(viewModel.projectModelList.value?.isNotEmpty() == true)
    }

    @Test
    fun `perform logout operation`() {
        viewModel.performLogout()

        assert(viewModel.logoutCompleted.value?.getContentIfNotHandled() == true)
    }
}
