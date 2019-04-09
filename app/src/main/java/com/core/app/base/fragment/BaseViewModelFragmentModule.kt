package com.core.app.base.fragment

import androidx.fragment.app.Fragment
import com.core.app.base.fragment.BaseFragmentModule
import dagger.Module

/**
 * Provides base fragment dependencies. This must be included in all fragment modules, which must
 * provide a concrete implementation of [Fragment].
 */
@Module(
        includes = arrayOf(
                BaseViewFragmentModule::class
        )
)
abstract class BaseViewModelFragmentModule
