package com.core.data

import com.core.data.network.AppRetrofitGatewayTest
import com.core.data.network.EntityDataTest
import com.core.data.network.gateway.retrofit.AppRetrofitGateway
import com.core.data.persistence.AccountDaoTest
import com.core.domain.interactor.*

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        AppRetrofitGatewayTest::class,
        EntityDataTest::class,
        AccountDaoTest::class
)
class DataTestSuite
