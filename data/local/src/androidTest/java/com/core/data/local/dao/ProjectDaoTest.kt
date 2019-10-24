package com.core.data.local.dao

import com.core.data.local.BaseLocalTest
import com.core.domain.Account
import com.core.domain.Project
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ProjectDaoTest : BaseLocalTest() {

    private lateinit var projectDao: ProjectDao

    override fun setUp() {
        super.setUp()

    }
}