package com.core.app.base

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import com.core.app.base.mvvm.ViewModelProviderFactory
import com.core.app.injector.scope.PerActivity
import dagger.Binds
import dagger.Module

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module(includes = arrayOf(BaseActivityModule::class))
abstract class BaseFragmentActivityModule