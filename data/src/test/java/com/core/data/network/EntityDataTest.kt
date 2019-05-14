package com.core.data.network

import com.core.commons.extension.fromJson
import com.core.data.BaseTest
import com.core.data.network.entity.ProjectEntity
import com.core.data.network.entity.UserEntity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raxdenstudios.commons.util.AssetsUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EntityDataTest : BaseTest() {

    private lateinit var gson: Gson

    @Before
    fun init() {
        gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Test
    fun entityUser() {
        gson.fromJson(AssetsUtils.getString(getContext(), "user.json"), UserEntity::class.java).run {
            Assert.assertNotNull(toUser())
        }
    }

    @Test
    fun entityProject() {
        gson.fromJson<List<ProjectEntity>>(AssetsUtils.getString(getContext(), "repos.json")).forEach {
            Assert.assertNotNull(it.toProject())
        }
    }
}