package com.core.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.core.common.test.BaseTest
import com.core.data.remote.retrofit.AppRetrofitGateway
import com.core.data.remote.retrofit.service.AppRetrofitService
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import org.junit.Before

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import java.io.File

abstract class BaseRemoteTest: BaseTest() {

    private lateinit var server: MockWebServer
    protected lateinit var gateway: AppGateway

    @Before
    override fun setUp() {
        super.setUp()
        initMockServer()
    }

    @After
    override fun tearDown() {
        super.tearDown()
        stopMockServer()
    }

    fun mockHttpResponse(fileName: String, responseCode: Int) = server.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path : String) : String {
        return this.javaClass.classLoader?.getResource(path)?.let {
            val file = File(it.path)
            return String(file.readBytes())
        } ?: ""
    }

    private fun initMockServer() {
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