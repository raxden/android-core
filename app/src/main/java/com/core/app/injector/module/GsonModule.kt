package com.core.app.injector.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Provides application-wide dependencies.
 */
@Module
object GsonModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun gsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun gson(builder: GsonBuilder): Gson {
        return builder.create()
    }

    @JvmStatic
    @Provides
    @Singleton
    @Named("excludeFieldsWithoutExposeAnnotation")
    internal fun excludeFieldsWithoutExposeAnnotationGson(builder: GsonBuilder): Gson {
        return builder.excludeFieldsWithoutExposeAnnotation().create()
    }

}
