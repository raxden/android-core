package com.core.data

import com.core.data.network.entity.UserEntity
import com.core.data.network.entity.mapper.UserEntityDataMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raxdenstudios.commons.util.AssetsUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EntityDataMapperTest : BaseTest() {

    private lateinit var gson: Gson

    @Before
    fun init() {
        gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Test
    fun entityUser() {
        val userEntity = gson.fromJson(AssetsUtils.getString(getContext(), "user.json"), UserEntity::class.java)
        val user = UserEntityDataMapper(getContext()).transform(userEntity)
        Assert.assertNotNull(user)
    }

}