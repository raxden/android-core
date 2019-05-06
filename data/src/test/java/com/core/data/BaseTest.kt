package com.core.data

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
abstract class BaseTest {

    fun getContext(): Context = InstrumentationRegistry.getInstrumentation().context
}
