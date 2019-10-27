package com.core.app.di.module

import com.core.features.splash.SplashActivityModule
import dagger.Module

@Module(
    includes = [
        SplashActivityModule::class
    ]
)
abstract class FeaturesModule
