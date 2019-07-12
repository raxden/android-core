package com.core.app.injector.module

import com.core.domain.interactor.*
import com.core.domain.interactor.impl.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UseCaseModule {

    @Binds
    @Singleton
    internal abstract fun getProjectListUseCase(useCase: GetProjectListUseCaseImpl): GetProjectListUseCase

    @Binds
    @Singleton
    internal abstract fun getProjectDetailUseCase(useCase: GetProjectDetailUseCaseImpl): GetProjectDetailUseCase

    @Binds
    @Singleton
    internal abstract fun loginUseCase(useCase: LoginUseCaseImpl): LoginUseCase

    @Binds
    @Singleton
    internal abstract fun logoutUseCase(useCase: LogoutUseCaseImpl): LogoutUseCase

    @Binds
    @Singleton
    internal abstract fun forwardUseCase(useCase: ForwardUseCaseImpl): ForwardUseCase

    @Binds
    @Singleton
    internal abstract fun getVersionUseCase(useCase: GetVersionUseCaseImpl): GetVersionUseCase
}
