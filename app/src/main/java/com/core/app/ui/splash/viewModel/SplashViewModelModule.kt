package com.core.app.ui.splash.viewModel

import androidx.lifecycle.ViewModel
import com.core.app.base.mvvm.BaseViewModelModule
import com.core.app.base.mvvm.ViewModelKey
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = arrayOf(
        BaseViewModelModule::class
))
abstract class SplashViewModelModule {

    @Binds
    @IntoMap
    @PerFragment
    @ViewModelKey(SplashViewModel::class)
    abstract fun viewModel(viewModel: SplashViewModel): ViewModel

}
