package com.core.domain.interactor
import com.core.domain.Account
import com.core.domain.BaseTest
import com.core.domain.Project
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

class GetProjectListUseCaseTest : BaseTest() {

    @Mock
    lateinit var accountRepository: AccountRepository
    @Mock
    lateinit var projectRepository: ProjectRepository

    @InjectMocks
    lateinit var getProjectListUseCase: GetProjectListUseCaseImpl

    private var projectList: List<Project> = mutableListOf<Project>().apply {
        add(Project(1, "project_1"))
        add(Project(2, "project_2"))
        add(Project(3, "project_3"))
        add(Project(4, "project_4"))
    }

    private val account: Account = Account(1234, "username")

    @Before
    fun init() {
        Mockito.`when`(accountRepository.retrieve()).thenReturn(Single.just(account))
        Mockito.`when`(projectRepository.list(account.username)).thenReturn(Maybe.just(projectList))
    }

    @Test
    fun projectListTest() {
        getProjectListUseCase.execute()
                .test()
                .assertNoErrors()
                .assertValue(projectList)
    }

    @Test
    fun projectListWithAccountParamTest() {
        getProjectListUseCase.execute("username")
                .test()
                .assertNoErrors()
                .assertValue(projectList)
    }
}