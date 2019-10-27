package com.core.features.splash

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashActivityModule {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    internal abstract fun activity(): SplashActivity
}