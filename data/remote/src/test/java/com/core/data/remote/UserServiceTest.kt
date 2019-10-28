package com.core.data.remote

import com.core.common.android.Result
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

class UserServiceTest : BaseRemoteTest() {

    @Test
    fun `fetch user's detail`() {
        runBlocking {
            mockHttpResponse("user.json", HttpURLConnection.HTTP_OK)
            when (val result = gateway.user("")) {
                is Result.Success -> {
                    Assert.assertEquals("raxden", result.data.login)
                    Assert.assertEquals(11458794L, result.data.id)
                }
                else -> assert(false)
            }
        }
    }

    @Test
    fun `fetch user's not found`() {
        runBlocking {
            mockHttpResponse("user_not_found.json", HttpURLConnection.HTTP_NOT_FOUND)
            when (gateway.user("")) {
                is Result.Error -> assert(true)
                else -> assert(false)
            }
        }
    }
}