package com.core.app.base.activity

import android.app.Activity
import dagger.Module

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module(includes = [BaseFragmentActivityModule::class])
abstract class BaseViewModelFragmentActivityModule