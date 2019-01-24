package com.core.app.ui.project.list.viewmodel

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
abstract class ProjectListViewModelModule {

    @Binds
    @IntoMap
    @PerFragment
    @ViewModelKey(ProjectListViewModel::class)
    abstract fun viewModel(viewModel: ProjectListViewModel): ViewModel
}
