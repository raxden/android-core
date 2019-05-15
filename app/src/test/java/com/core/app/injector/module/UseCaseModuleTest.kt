package com.core.app.injector.module

import com.core.domain.Account
import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.LoginUseCase
import com.core.domain.interactor.impl.GetProjectDetailUseCaseImpl
import com.core.domain.interactor.impl.GetProjectListUseCaseImpl
import com.core.domain.interactor.impl.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.Mockito
import java.lang.RuntimeException
import javax.inject.Singleton

@Module
object UseCaseModuleTest {

    @JvmStatic
    @Provides
    @Singleton
    internal fun getProjectListUseCaseImpl(): GetProjectListUseCaseImpl = Mockito.mock(GetProjectListUseCaseImpl::class.java).also {

    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun getProjectDetailUseCaseImpl(): GetProjectDetailUseCaseImpl = Mockito.mock(GetProjectDetailUseCaseImpl::class.java).also {

    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun loginUseCaseImpl(): LoginUseCaseImpl = Mockito.mock(LoginUseCaseImpl::class.java).also {
        Mockito.`when`(it.execute("")).thenThrow(RuntimeException("empty user"))
        Mockito.`when`(it.execute("username")).thenReturn(Single.just(Account(1234, "username")))
    }
}
