package com.core.domain

import com.core.domain.interactor.*

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        LoginUseCaseTest::class,
        GetProjectListUseCaseTest::class,
        GetProjectDetailUseCaseTest::class
)
class DomainTestSuite
