package com.core.app.injector.module

import com.core.domain.interactor.GetProjectDetailUseCase
import com.core.domain.interactor.GetProjectListUseCase
import com.core.domain.interactor.impl.GetProjectDetailUseCaseImpl
import com.core.domain.interactor.impl.GetProjectListUseCaseImpl
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

}
