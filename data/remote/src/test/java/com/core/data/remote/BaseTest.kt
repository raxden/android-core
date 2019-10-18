package com.core.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.data.remote.gateway.AppGateway
import com.core.data.remote.gateway.retrofit.AppRetrofitGateway
import com.core.data.remote.gateway.retrofit.service.AppRetrofitService
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import org.junit.Before

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Rule
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.io.File

abstract class BaseTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var server: MockWebServer
    protected lateinit var gateway: AppGateway

    @Before
    fun setUp() {
        configureMockServer()
    }

    @After
    fun tearDown() {
        stopMockServer()
    }

    fun mockHttpResponse(fileName: String, responseCode: Int) = server.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path : String) : String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    private fun configureMockServer() {
        server = MockWebServer()
        server.start()

        val service = Retrofit.Builder()
            .baseUrl(server.url(("/")))
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

    private fun stopMockServer() {
        server.shutdown()
    }
}