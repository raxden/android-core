package com.core.app.injector.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.app.util.ViewModelKey
import com.core.app.util.ViewModelProviderFactory
import com.core.app.injector.scope.PerActivity
import com.core.app.ui.screens.login.LoginViewModel
import com.core.app.ui.screens.home.HomeViewModel
import com.core.app.ui.screens.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @PerActivity
    abstract fun viewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @PerActivity
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun projectListViewModel(viewModel: HomeViewModel): ViewModel
}
