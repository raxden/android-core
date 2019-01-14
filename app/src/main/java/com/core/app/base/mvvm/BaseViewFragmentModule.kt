package com.core.app.base.mvvm

import androidx.fragment.app.Fragment
import com.core.app.base.BaseFragmentModule
import dagger.Module

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

}
