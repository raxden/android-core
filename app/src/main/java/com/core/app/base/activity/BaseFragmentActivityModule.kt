package com.core.app.base.activity

import android.app.Activity
import com.core.app.base.activity.BaseActivityModule
import dagger.Module

/**
 * Provides base activity dependencies. This must be included in all activity modules, which must
 * provide a concrete implementation of [Activity].
 */
@Module(includes = [BaseActivityModule::class])
abstract class BaseFragmentActivityModule