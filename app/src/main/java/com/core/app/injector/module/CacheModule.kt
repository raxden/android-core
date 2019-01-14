package com.core.app.injector.module

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import java.io.File
import javax.inject.Singleton

@Module
object CacheModule {

    private const val CACHE_DIRECTORY = "responses"
    private const val CACHE_SIZE = 10 * 1024 * 1024         // 10 MiB;

    @JvmStatic
    @Provides
    @Singleton
    internal fun cache(context: Context): Cache {
        return Cache(File(context.cacheDir, CACHE_DIRECTORY), CACHE_SIZE.toLong())
    }

}
