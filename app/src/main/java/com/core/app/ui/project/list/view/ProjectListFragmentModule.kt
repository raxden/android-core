package com.core.app.ui.project.list.view

import androidx.fragment.app.Fragment
import com.core.app.base.BaseFragmentModule
import com.core.app.base.mvvm.BaseViewFragmentModule
import com.core.app.injector.scope.PerFragment
import com.core.app.ui.project.list.viewModel.ProjectListViewModelModule
import dagger.Binds
import dagger.Module

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = arrayOf(
        BaseViewFragmentModule::class,
        ProjectListViewModelModule::class
))
abstract class ProjectListFragmentModule {

    /**
     * As per the contract specified in [BaseFragmentModule]; "This must be included in all
     * fragment modules, which must provide a concrete implementation of [Fragment].
     *
     * @param fragment the SampleMapFragment
     * @return the fragment
     */
    @Binds
    @PerFragment
    internal abstract fun fragment(fragment: ProjectListFragment): Fragment
}
