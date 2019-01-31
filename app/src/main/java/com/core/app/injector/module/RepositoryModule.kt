package com.core.app.injector.module

import com.core.data.repository.ProjectRepositoryImpl
import com.core.data.repository.UserRepositoryImpl
import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun projectRepository(repository: ProjectRepositoryImpl): ProjectRepository

    @Binds
    @Singleton
    internal abstract fun userRepository(repository: UserRepositoryImpl): UserRepository

}
