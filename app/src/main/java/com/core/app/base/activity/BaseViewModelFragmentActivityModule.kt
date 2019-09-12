package com.core.app.base.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.core.app.injector.scope.PerActivity
import com.core.app.util.ViewModelManager
import com.core.app.util.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module(includes = [BaseFragmentActivityModule::class])
abstract class BaseViewModelFragmentActivityModule {

    @Binds
    @PerActivity
    abstract fun viewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Module
    companion object {

        @JvmStatic
        @Provides
        @PerActivity
        internal fun viewModelManager(
                activity: AppCompatActivity,
                factory: ViewModelProvider.Factory
        ): ViewModelManager = ViewModelManager(activity, factory)
    }
}