package com.core.data

import com.core.data.network.AppGatewayTest
import com.core.data.persistence.AccountDaoTest

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        AppGatewayTest::class,
        AccountDaoTest::class
)
class DataTestSuite
