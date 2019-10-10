package com.core.app.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.core.app.RxSchedulerRule
import com.core.app.model.ProjectModel
import com.core.app.ui.screens.home.HomeViewModel
import com.core.commons.Event
import com.core.domain.Project
import com.core.domain.User
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
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
    private lateinit var projectSelectedObserver: Observer<Event<Project>>
    @Mock
    private lateinit var logoutCompletedObserver: Observer<Event<Boolean>>
    @Mock
    private lateinit var userObserver: Observer<User>
    @Mock
    private lateinit var projectModelListObserver: Observer<List<ProjectModel>>
    @Mock
    private lateinit var statusObserver: Observer<Boolean>
    @Mock
    private lateinit var throwableObserver: Observer<Throwable>

    @Captor
    lateinit var projectModelListCaptor: ArgumentCaptor<List<ProjectModel>>
    @Captor
    lateinit var booleanEventCaptor: ArgumentCaptor<Event<Boolean>>

    private lateinit var homeViewModel: HomeViewModel

    private val user = User(username = "raxden")
    private val projectList = listOf(
            Project(1, "name_1"),
            Project(2, "name_2"),
            Project(3, "name_3")
    )
    private val throwable = Throwable()

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(getProjectListUseCase, logoutUseCase)

        homeViewModel.projectSelected.observeForever(projectSelectedObserver)
        homeViewModel.logoutCompleted.observeForever(logoutCompletedObserver)
        homeViewModel.user.observeForever(userObserver)
        homeViewModel.projectModelList.observeForever(projectModelListObserver)
        homeViewModel.loader.status.observeForever(statusObserver)
        homeViewModel.throwable.observeForever(throwableObserver)
    }

    @Test
    fun `retrieve results after setting user to viewModel`() {
        `when`(getProjectListUseCase.execute(user.username)).thenReturn(Maybe.just(projectList))

        homeViewModel.setUser(user)

        inOrder(projectModelListObserver).apply {
            verify(projectModelListObserver).onChanged(projectModelListCaptor.capture())
            projectList.forEachIndexed { index, project ->
                assert(project.name == projectModelListCaptor.value[index].name)
            }
        }
        inOrder(statusObserver).apply {
            verify(statusObserver).onChanged(true)
            verify(statusObserver).onChanged(false)
        }
        inOrder(throwableObserver).apply {
            verify(throwableObserver, never()).onChanged(throwable)
        }
    }

    @Test
    fun `perform logout operation`() {
        `when`(logoutUseCase.execute()).thenReturn(Completable.complete())

        homeViewModel.performLogout()

        inOrder(logoutCompletedObserver).apply {
            verify(logoutCompletedObserver).onChanged(booleanEventCaptor.capture())
            assert(booleanEventCaptor.value.peekContent())
        }
        inOrder(throwableObserver).apply {
            verify(throwableObserver, never()).onChanged(throwable)
        }
    }
}
