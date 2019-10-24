package com.core.data.remote

import com.core.common.android.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

class ProjectServiceTest: BaseRemoteTest() {

    @Test
    fun `fetch project's detail`() {
        runBlocking {
            mockHttpResponse("repo.json", HttpURLConnection.HTTP_OK)
            when (val result = gateway.project("", "")) {
                is Result.Success -> {
                    Assert.assertEquals("android-core", result.data.name)
                    Assert.assertEquals(165669612L, result.data.id)
                }
                else -> assert(false)
            }
        }
    }

    @Test
    fun `fetch project's list`() {
        runBlocking {
            mockHttpResponse("repos.json", HttpURLConnection.HTTP_OK)
            when (val result = gateway.projectList("")) {
                is Result.Success -> {
                    assert(result.data.isNotEmpty())
                }
                else -> assert(false)
            }
        }
    }
}