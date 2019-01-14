package com.core.app.base.mvvm

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.app.base.BaseFragmentModule
import com.core.app.injector.scope.PerFragment
import com.core.app.ui.project.list.ProjectListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(
        includes = arrayOf(
                BaseFragmentModule::class
        )
)
abstract class BaseViewFragmentModule {

    @Binds
    @IntoMap
    @PerFragment
    @ViewModelKey(ProjectListViewModel::class)
    internal abstract fun bindViewModel(myViewModel: ProjectListViewModel): ViewModel

    @Binds
    @PerFragment
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}
