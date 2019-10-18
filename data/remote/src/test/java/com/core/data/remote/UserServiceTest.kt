package com.core.data.remote

import com.core.data.model.ErrorEntity
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection

class UserServiceTest : BaseTest() {

    @Test
    fun `fetch user's detail`() {
        runBlocking {
            mockHttpResponse("user.json", HttpURLConnection.HTTP_OK)
            val response = gateway.user("")
            assert(response.isSuccessful)
            response.body()?.let { user ->
                Assert.assertEquals("raxden", user.login)
                Assert.assertEquals(11458794L, user.id)
            }
        }
    }

    @Test
    fun `fetch user's not found`() {
        runBlocking {
            mockHttpResponse("user_not_found.json", HttpURLConnection.HTTP_NOT_FOUND)
            val response = gateway.user("")
            assert(!response.isSuccessful)
            response.errorBody()?.let {
                val errorEntity = Gson().fromJson(it.string(), ErrorEntity::class.java)
                Assert.assertEquals("Not Found", errorEntity.message)
            }
        }
    }
}