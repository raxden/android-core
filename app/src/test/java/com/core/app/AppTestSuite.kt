package com.core.app

import com.core.app.ui.home.HomeViewModelTest
import com.core.app.ui.login.LoginViewModelTest
import com.core.app.ui.splash.SplashViewModelTest
import com.core.domain.interactor.*

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
        SplashViewModelTest::class,
        LoginViewModelTest::class,
        HomeViewModelTest::class
)
class AppTestSuite
