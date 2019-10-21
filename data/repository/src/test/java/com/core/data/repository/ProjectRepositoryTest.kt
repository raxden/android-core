package com.core.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.core.common.android.Resource
import com.core.common.android.Result
import com.core.common.test.rules.CoroutinesMainDispatcherRule
import com.core.common.test.rules.TestReportingTree
import com.core.data.local.dao.ProjectDao
import com.core.data.remote.AppGateway
import com.core.data.remote.entity.ProjectEntity
import com.core.data.repository.mapper.ProjectDataMapper
import com.core.data.repository.mapper.UserDataMapper
import com.core.domain.Project
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import timber.log.Timber

class ProjectRepositoryTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    var coroutinesTestRule = CoroutinesMainDispatcherRule()

    @MockK
    private lateinit var gateway: AppGateway
    @MockK
    private lateinit var dao: ProjectDao
    @RelaxedMockK
    private lateinit var projectListObserver: Observer<Resource<List<Project>>>

    private lateinit var projectRepository: ProjectRepositoryImpl

    private var projectEntitySampleList = listOf(
        ProjectEntity(1),
        ProjectEntity(2),
        ProjectEntity(3)
    )

    private var projectSampleList = listOf(
        Project(1),
        Project(2),
        Project(3)
    )

    @Before
    fun setUp() {
        Timber.plant(TestReportingTree())
        MockKAnnotations.init(this)

        val userDataMapper = UserDataMapper()
        val projectDataMapper = ProjectDataMapper(userDataMapper)

        projectRepository = ProjectRepositoryImpl(gateway, dao, projectDataMapper)
    }

    @Test
    fun `Get projects from network`() {
        coEvery { gateway.projectList("raxden") } returns Result.Success(projectEntitySampleList)
        coEvery { dao.findAll(any()) } returns listOf() andThen projectSampleList
        coEvery { dao.insert(*anyVararg()) } returns Unit

        runBlocking {
            projectRepository.list("raxden").observeForever(projectListObserver)
        }

        verifyOrder {
            projectListObserver.onChanged(Resource.loading(null)) // Init loading with no value
            projectListObserver.onChanged(Resource.loading(listOf())) // Then trying to load from db (fast temp loading) before load from remote source
            projectListObserver.onChanged(Resource.success(projectSampleList)) // retrofit data loaded
        }
        confirmVerified(projectListObserver)
    }

    @Test
    fun `Get projects from server when no internet is available`() {
        val exception = Exception("Internet")
        coEvery { gateway.projectList("raxden") } returns Result.Error(exception)
        coEvery { dao.findAll(any()) } returns projectSampleList

        runBlocking {
            projectRepository.list("raxden").observeForever(projectListObserver)
        }

        verifyOrder {
            projectListObserver.onChanged(Resource.loading(null)) // Init loading with no value
            projectListObserver.onChanged(Resource.loading(projectSampleList)) // Then trying to load from db (fast temp loading) before load from remote source
            projectListObserver.onChanged(Resource.error(exception, projectSampleList)) // retrofit data loaded
        }
        confirmVerified(projectListObserver)
    }
}