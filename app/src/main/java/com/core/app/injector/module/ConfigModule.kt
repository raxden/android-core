package com.core.app.injector.module

import com.core.app.BuildConfig
import com.core.app.R
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ConfigModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun remoteConfigSettings(): FirebaseRemoteConfigSettings {
        return if (BuildConfig.DEBUG) FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(4200)
                .build()
        else FirebaseRemoteConfigSettings.Builder()
                .build()
    }

    @JvmStatic
    @Provides
    @Singleton
    internal fun remoteConfig(settings: FirebaseRemoteConfigSettings): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(settings)
        setDefaults(R.xml.remote_config_defaults)
        fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {

            }
        }
    }
}
