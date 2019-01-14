package com.core.app.injector.module

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
object LocaleModule {

    @JvmStatic
    @Provides
    @IntoSet
    @Singleton
    internal fun localeES(): Locale {
        return Locale("es", "ES")
    }

    @JvmStatic
    @Provides
    @IntoSet
    @Singleton
    internal fun localeCA(): Locale {
        return Locale("ca", "ES")
    }

    @JvmStatic
    @Provides
    @Singleton
    @Named("default")
    internal fun localeDefault(): Locale {
        return Locale("es", "ES")
    }

}
