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
import javax.inject.Singleton

@Module
object UseCaseModuleTest {

    @JvmStatic
    @Provides
    @Singleton
    internal fun getProjectListUseCaseImpl(): GetProjectListUseCase = Mockito.mock(GetProjectListUseCaseImpl::class.java).also {

    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun getProjectDetailUseCaseImpl(): GetProjectDetailUseCase = Mockito.mock(GetProjectDetailUseCaseImpl::class.java).also {

    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun loginUseCaseImpl(): LoginUseCase = Mockito.mock(LoginUseCaseImpl::class.java).also {
        Mockito.`when`(it.execute("")).thenReturn(Single.just(Account(1234, "username")))
    }
}
