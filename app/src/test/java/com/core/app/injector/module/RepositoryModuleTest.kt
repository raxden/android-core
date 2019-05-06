package com.core.app.injector.module

import com.core.data.repository.AccountRepositoryImpl
import com.core.data.repository.ProjectRepositoryImpl
import com.core.data.repository.UserRepositoryImpl
import com.core.domain.Account
import com.core.domain.User
import dagger.Module
import dagger.Provides
import io.reactivex.Maybe
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
    internal fun accountRepositoryImpl(): AccountRepositoryImpl = Mockito.mock(AccountRepositoryImpl::class.java).also {
        Mockito.`when`(it.save(anyObject())).thenReturn(Single.just(Account(1234, "username")))
    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun userRepositoryImpl(): UserRepositoryImpl = Mockito.mock(UserRepositoryImpl::class.java).also {
        Mockito.`when`(it.retrieve("username")).thenReturn(Maybe.just(User(1234, "username")))
    }

    private fun <T> anyObject(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    private fun <T> uninitialized(): T = null as T
}
