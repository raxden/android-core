package com.core.app.injector.module

import com.core.data.repository.ProjectRepositoryImpl
import com.core.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun projectRepository(repository: ProjectRepositoryImpl): ProjectRepository

}
