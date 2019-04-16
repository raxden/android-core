package com.core.app.injector.module

import com.core.data.repository.ProjectRepositoryImpl
import com.core.data.repository.UserRepositoryImpl
import com.core.domain.User
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import org.mockito.Mockito
import javax.inject.Singleton

@Module
object RepositoryModuleTest {

    @JvmStatic
    @Provides
    @Singleton
    internal fun projectRepositoryImpl(): ProjectRepositoryImpl = Mockito.mock(ProjectRepositoryImpl::class.java)

    @JvmStatic
    @Provides
    @Singleton
    internal fun userRepositoryImpl(): UserRepositoryImpl = Mockito.mock(UserRepositoryImpl::class.java).also {
        Mockito.`when`(it.login("username", "password")).thenReturn(Single.just(User(1234, "username")))
        Mockito.`when`(it.retrieve()).thenReturn(Single.just(User(1234, "username")))
    }
}
