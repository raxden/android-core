package com.core.domain.interactor
import com.core.domain.Account
import com.core.domain.BaseTest
import com.core.domain.Project
import com.core.domain.interactor.impl.GetProjectDetailUseCaseImpl
import com.core.domain.interactor.impl.GetProjectListUseCaseImpl
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.ProjectRepository
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class GetProjectDetailUseCaseTest : BaseTest() {

    @Mock
    lateinit var accountRepository: AccountRepository
    @Mock
    lateinit var projectRepository: ProjectRepository

    @InjectMocks
    lateinit var getProjectDetailUseCase: GetProjectDetailUseCaseImpl

    private val project: Project = Project(1234, "projectName")
    private val account: Account = Account(1234, "username")

    @Before
    fun init() {
        Mockito.`when`(accountRepository.retrieve()).thenReturn(Single.just(account))
        Mockito.`when`(projectRepository.detail(account.username, project.name!!)).thenReturn(Single.just(project))
    }

    @Test
    fun projectDetailTest() {
        getProjectDetailUseCase.execute(project.name!!)
                .test()
                .assertNoErrors()
                .assertValue(project)
    }

    @Test
    fun projectWithAccountParamTest() {
        getProjectDetailUseCase.execute(account.username, project.name!!)
                .test()
                .assertNoErrors()
                .assertValue(project)
    }
}