package com.core.data.network

import android.text.TextUtils
import com.core.commons.AssetsUtils
import com.core.data.BaseTest
import com.core.data.network.entity.ErrorEntity
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.core.data.network.gateway.AppGateway
import com.core.data.network.gateway.retrofit.AppRetrofitGateway
import com.core.data.network.gateway.retrofit.adapter.RxErrorHandlingCallAdapterFactory
import com.core.data.network.gateway.retrofit.exception.RetrofitException
import com.core.data.network.gateway.retrofit.service.AppRetrofitService
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Test

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class AppGatewayTest : BaseTest() {

    private lateinit var server: MockWebServer
    private lateinit var gateway: AppGateway

    @Before
    fun initGateway() {
        server = MockWebServer().apply {
            start()
        }
        val service = Retrofit.Builder()
                .baseUrl(server.url(("/")))
                .addCallAdapterFactory(
                        RxErrorHandlingCallAdapterFactory.create()
                )
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create()
                )
                .addConverterFactory(
                        GsonConverterFactory.create(GsonBuilder()
                                .excludeFieldsWithoutExposeAnnotation()
                                .create()
                        )
                )
                .build()
                .create(AppRetrofitService::class.java)
        gateway = AppRetrofitGateway(service)
    }

    @After
    fun closeGateway() {
        server.shutdown()
    }

    @Test
    fun serverError() {
        server.enqueue(MockResponse().apply { setResponseCode(500) })
        gateway.user("").test().assertError { (it as RetrofitException).kind == RetrofitException.Kind.SERVER_ERROR }
    }

    @Test
    fun unexpectedError() {
        server.enqueue(MockResponse().apply { setResponseCode(600) })
        gateway.user("").test().assertError { (it as RetrofitException).kind == RetrofitException.Kind.UNEXPECTED }
    }

    @Test
    fun unauthenticatedError() {
        server.enqueue(MockResponse().apply { setResponseCode(401) })
        gateway.user("").test().assertError { (it as RetrofitException).kind == RetrofitException.Kind.UNAUTHENTICATED }
    }

    @Test
    fun clientError() {
        server.enqueue(MockResponse().apply { setResponseCode(400) })
        gateway.user("").test().assertError { (it as RetrofitException).kind == RetrofitException.Kind.CLIENT_ERROR }
    }

    @Test
    fun userEntity() {
        server.enqueue(
                MockResponse().apply {
                    setResponseCode(200)
                    setBody(AssetsUtils.getString(getContext(), "user.json"))
                }
        )
        gateway.user("").test()
                .assertValue { validateUserEntity(it) }
                .assertNoErrors()
    }

    @Test
    fun userEntityNotFound() {
        server.enqueue(
                MockResponse().apply {
                    setResponseCode(404)
                    setBody(AssetsUtils.getString(getContext(), "user_not_found.json"))
                }
        )
        gateway.user("").test().assertError {
            (it as RetrofitException).let { exception ->
                exception.kind == RetrofitException.Kind.CLIENT_ERROR
                        && exception.parseBodyErrorAs(ErrorEntity::class.java)?.let { entity -> entity.message == "Not Found" } ?: false
            }
        }
    }

    @Test
    fun projectEntityList() {
        server.enqueue(
                MockResponse().apply {
                    setResponseCode(200)
                    setBody(AssetsUtils.getString(getContext(), "repos.json"))
                }
        )
        gateway.projectList("").test()
                .assertValue { list: List<ProjectEntity> ->
                    list.find { !validateProjectEntity(it) }?.let { false } ?: true
                }
                .assertNoErrors()
    }

    @Test
    fun projectEntity() {
        server.enqueue(
                MockResponse().apply {
                    setResponseCode(200)
                    setBody(AssetsUtils.getString(getContext(), "repo.json"))
                }
        )
        gateway.project("", "").test()
                .assertValue { validateProjectEntity(it) }
                .assertNoErrors()
    }

    private fun validateUserEntity(entity: UserEntity): Boolean = entity.id != null
            && !TextUtils.isEmpty(entity.name)

    private fun validateProjectEntity(entity: ProjectEntity): Boolean = entity.id != null
            && !TextUtils.isEmpty(entity.name)
}