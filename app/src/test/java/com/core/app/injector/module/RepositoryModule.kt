package com.core.app.injector.module

import com.core.domain.repository.ProjectRepository
import com.core.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun projectRepository(): ProjectRepository = Mockito.mock(ProjectRepository::class.java)

    @JvmStatic
    @Provides
    @Singleton
    internal fun userRepository(): UserRepository = Mockito.mock(UserRepository::class.java)
}
