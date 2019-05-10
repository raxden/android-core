package com.core.app.injector.module

import android.content.Context
import com.google.gson.Gson
import com.core.app.BuildConfig
import com.core.app.R
import com.core.data.network.gateway.AppGateway
import com.core.data.network.gateway.retrofit.AppFileStreamGateway
import com.core.data.network.gateway.retrofit.AppRetrofitGateway
import com.core.data.network.gateway.retrofit.adapter.RxErrorHandlingCallAdapterFactory
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import com.core.data.network.interceptor.HttpCacheInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val CACHE_DIRECTORY = "responses"
    private const val CACHE_SIZE = 10 * 1024 * 1024         // 10 MiB;
    private const val CACHE_MAX_AGE = 60 * 10               // read from cache for 10 minutes
    private const val CACHE_MAX_STALE = 60 * 60 * 24 * 28   // tolerate 4-weeks stale
    private const val TIMEOUT = 35                          // 30 sec
    private const val CONNECTION_TIMEOUT = 15               // 10 sec

    @JvmStatic
    @Provides
    @Singleton
    internal fun httpLoggingInterceptorLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
    }

    // Interceptors ====================================================================================================

    @JvmStatic
    @Provides
    @Singleton
    internal fun httpCacheInterceptor(context: Context): HttpCacheInterceptor {
        return HttpCacheInterceptor(context).apply {
            cacheMaxAge = CACHE_MAX_AGE
            cacheMaxStale = CACHE_MAX_STALE
        }
    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun httpLoggingInterceptor(logginLevel: HttpLoggingInterceptor.Level): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = logginLevel
        }
    }

    // OKHttpClient ====================================================================================================

    @JvmStatic
    @Provides
    @Singleton
    internal fun cache(context: Context): Cache {
        return Cache(File(context.cacheDir, CACHE_DIRECTORY), CACHE_SIZE.toLong())
    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun httpClient(httpLoggingInterceptor: HttpLoggingInterceptor, cache: Cache, httpCacheInterceptor: HttpCacheInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(httpCacheInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
    }

    // Retrofit ========================================================================================================

    @JvmStatic
    @Provides
    @Singleton
    internal fun retrofit(context: Context, httpClient: OkHttpClient, @Named("excludeFieldsWithoutExposeAnnotation") gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.data_api_domain))
                .client(httpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    // RetrofitService =================================================================================================

    @JvmStatic
    @Provides
    @Singleton
    internal fun appRetrofitService(retrofit: Retrofit): AppRetrofitService {
        return retrofit.create(AppRetrofitService::class.java)
    }

    // Gateway =========================================================================================================

    @JvmStatic
    @Provides
    @Singleton
    internal fun appGateway(context: Context, @Named("excludeFieldsWithoutExposeAnnotation") gson: Gson, service: AppRetrofitService): AppGateway {
        return when {
            BuildConfig.FLAVOR == "mock" -> AppFileStreamGateway(context, gson)
            else -> AppRetrofitGateway(service)
        }
    }
}
