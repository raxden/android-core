package com.core.data.network.gateway

import android.content.Context
import android.text.TextUtils
import com.core.data.BaseTest
import com.core.data.R
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.data.network.gateway.retrofit.AppRetrofitGateway
import com.core.data.network.gateway.retrofit.adapter.RxErrorHandlingCallAdapterFactory
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import com.core.data.network.interceptor.HttpCacheInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class AppGatewayTest : BaseTest() {

    private val CACHE_DIRECTORY = "responses"
    private val CACHE_SIZE = 10 * 1024 * 1024         // 10 MiB;
    private val CACHE_MAX_AGE = 60 * 10               // read from cache for 10 minutes
    private val CACHE_MAX_STALE = 60 * 60 * 24 * 28   // tolerate 4-weeks stale
    private val TIMEOUT = 35                          // 30 sec
    private val CONNECTION_TIMEOUT = 15               // 10 sec

    private lateinit var gateway: AppGateway

    @Before
    fun initGateway() {
        gateway = appGateway()
    }

    @Test
    fun retrieveUserEntity() {
        gateway.user("raxden").test().assertValue { entity: UserEntity ->
            validateUserEntity(entity)
        }
    }

    @Test
    fun retrieveProjectEntityList() {
        gateway.projectList("raxden").test().assertValue { entityList: List<ProjectEntity> ->
            entityList.find { !validateProjectEntity(it) }?.let { false } ?: true
        }
    }

    @Test
    fun retrieveProjectEntity() {
        gateway.project("raxden", "android-core").test().assertValue { entity: ProjectEntity ->
            validateProjectEntity(entity)
        }
    }

    private fun validateUserEntity(entity: UserEntity): Boolean {
        return entity.id != null && !TextUtils.isEmpty(entity.name)
    }

    private fun validateProjectEntity(entity: ProjectEntity): Boolean {
        return entity.id != null && !TextUtils.isEmpty(entity.name)
    }

    private fun httpCacheInterceptor(context: Context): HttpCacheInterceptor {
        return HttpCacheInterceptor(context).apply {
            cacheMaxAge = CACHE_MAX_AGE
            cacheMaxStale = CACHE_MAX_STALE
        }
    }

    private fun cache(context: Context): Cache {
        return Cache(File(context.cacheDir, CACHE_DIRECTORY), CACHE_SIZE.toLong())
    }

    private fun httpClient(cache: Cache, httpCacheInterceptor: HttpCacheInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(httpCacheInterceptor)
                .cache(cache)
                .retryOnConnectionFailure(true)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
    }

    private fun gson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    private fun retrofit(context: Context, httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.data_api_domain))
                .client(httpClient)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }

    private fun appRetrofitService(retrofit: Retrofit): AppRetrofitService {
        return retrofit.create(AppRetrofitService::class.java)
    }

    private fun appGateway(): AppGateway {
        val httpCacheInterceptor = httpCacheInterceptor(getContext())
        val cache = cache(getContext())
        val httpClient = httpClient(cache, httpCacheInterceptor)
        val retrofit = retrofit(getContext(), httpClient, gson())
        val service = appRetrofitService(retrofit)
        return AppRetrofitGateway(service)
    }
}