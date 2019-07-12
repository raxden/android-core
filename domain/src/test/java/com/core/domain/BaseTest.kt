package com.core.domain

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
abstract class BaseTest {

    @get:Rule
    var injectMocksRule: TestRule = TestRule { base, _ ->
        MockitoAnnotations.initMocks(this@BaseTest)
        base
    }

    fun getContext(): Context = InstrumentationRegistry.getInstrumentation().context
}
