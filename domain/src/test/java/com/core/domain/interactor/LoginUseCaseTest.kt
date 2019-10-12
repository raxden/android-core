package com.core.domain.interactor

import com.core.commons.MockitoUtils
import com.core.domain.Account
import com.core.domain.User
import com.core.domain.interactor.impl.LoginUseCaseImpl
import com.core.domain.repository.AccountRepository
import com.core.domain.repository.UserRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LoginUseCaseTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var accountRepository: AccountRepository
    @Mock
    lateinit var userRepository: UserRepository

    @InjectMocks
    lateinit var loginUseCase: LoginUseCaseImpl

    private val user: User = User(1234, "username")
    private val account: Account = Account(1234, "username")

    @Before
    fun init() {
        Mockito.`when`(userRepository.retrieve("username")).thenReturn(Single.just(user))
        Mockito.`when`(accountRepository.save(MockitoUtils.anyObject())).thenReturn(Single.just(account))
    }

    @Test
    fun loginTest() {
        loginUseCase.execute("username")
                .test()
                .assertNoErrors()
                .assertValue(user)
    }
}