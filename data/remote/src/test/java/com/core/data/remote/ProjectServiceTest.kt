package com.core.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

class ProjectServiceTest: BaseTest() {

    @Test
    fun `fetch project's detail`() {
        runBlocking {
            mockHttpResponse("repo.json", HttpURLConnection.HTTP_OK)
            val response = gateway.project("", "")
            assert(response.isSuccessful)
            response.body()?.let { project ->
                Assert.assertEquals("android-core", project.name)
                Assert.assertEquals(165669612L, project.id)
            }
        }
    }

    @Test
    fun `fetch project's list`() {
        runBlocking {
            mockHttpResponse("repos.json", HttpURLConnection.HTTP_OK)
            val response = gateway.projectList("")
            assert(response.isSuccessful)
            response.body()?.let { list ->
                assert(list.isNotEmpty())
            }
        }
    }
}