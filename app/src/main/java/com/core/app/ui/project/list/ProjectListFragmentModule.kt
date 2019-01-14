package com.core.app.ui.project.list

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.core.app.base.BaseFragmentModule
import com.core.app.base.mvvm.BaseViewFragmentModule
import com.core.app.base.mvvm.ViewModelKey
import com.core.app.injector.scope.PerFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Provides SampleMapFragment fragment dependencies.
 */
@Module(includes = arrayOf(
        BaseViewFragmentModule::class
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
