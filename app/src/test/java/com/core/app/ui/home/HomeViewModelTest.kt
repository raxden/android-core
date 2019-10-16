package com.core.app.ui.home

import androidx.lifecycle.Observer
import com.core.app.CoroutinesTestRule
import com.core.app.model.ProjectModel
import com.core.app.ui.screens.home.HomeViewModel
import com.core.commons.Event
import com.core.commons.Resource
import com.core.domain.Project
import com.core.domain.User
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LogoutUseCase
import io.reactivex.Completable
import io.reactivex.Maybe
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

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
        homeViewModel = HomeViewModel(getProjectListUseCase, logoutUseCase).also {
            it.projectSelected.observeForever(projectSelectedObserver)
            it.logoutCompleted.observeForever(logoutCompletedObserver)
            it.user.observeForever(userObserver)
            it.projectModelList.observeForever(projectModelListObserver)
            it.loader.status.observeForever(statusObserver)
            it.throwable.observeForever(throwableObserver)
        }
    }

    @Test
    fun `retrieve results after setting user to viewModel`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(getProjectListUseCase.execute(user.username)).thenReturn(Resource.success(projectList))

            homeViewModel.setUser(user)

            inOrder(statusObserver).apply {
                verify(statusObserver).onChanged(true)
                verify(statusObserver).onChanged(false)
            }

            projectModelListCaptor.run {
                verify(projectModelListObserver).onChanged(capture())
                projectList.forEachIndexed { index, project ->
                    assert(project.name == value[index].name)
                }
            }

            verify(throwableObserver, never()).onChanged(throwable)
        }
    }

    @Test
    fun `perform logout operation`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {
            `when`(logoutUseCase.execute())

            homeViewModel.performLogout()

            booleanEventCaptor.run {
                verify(logoutCompletedObserver).onChanged(capture())
                assert(value.peekContent())
            }

            verify(throwableObserver, never()).onChanged(throwable)
        }
    }
}
