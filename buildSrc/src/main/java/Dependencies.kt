object ApplicationId {
    val id = "com.core.app"
}

object Modules {
    val app = ":app"

    val navigation = ":navigation"

    val commonAndroid = ":common:android"
    val commonDagger = ":common:dagger"
    val commonTest = ":common:test"

    val domain = ":domain"

    val dataLocal = ":data:local"
    val dataRemote = ":data:remote"
    val dataModel = ":data:model"
    val dataRepository = ":data:repository"

    val featureSplash = ":features:splash"
    val featureLogin = ":features:login"
    val featureHome = ":features:home"
    val featureDetail = ":features:detail"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Sdk {
}

object Versions {
    val minSdk = 21
    val compileSdk = 28
    val targetSdk = 28
    val kotlin = "1.3.50"
    val gradle = "3.5.1"
    val fabric = "1.28.1"
    val room = "2.2.0"                      // https://developer.android.com/jetpack/androidx/releases/room
    val gson = "2.8.6"                      // https://github.com/google/gson
    val okHttp = "4.2.1"                    // https://github.com/square/okhttp
    val retrofit = "2.6.2"                  // https://github.com/square/retrofit
    val atsl = "1.1.1"
    val coroutines = "1.3.2"
    val archCoreTest = "2.0.0"
    val dagger = "2.22.1"                   // https://github.com/google/dagger
    val timber = "4.7.1"                    // https://github.com/JakeWharton/timber
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    //val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}

object Libraries {
    // Dagger
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Room
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Retrofit
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // Timber
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    // ANDROID TEST
    val archCoreTest = "androidx.arch.core:core-testing:${Versions.archCoreTest}"
    val atslJunit = "androidx.test.ext:junit:${Versions.atsl}"
    val atslRunner = "androidx.test:runner:${Versions.atsl}"
    val atslRules = "androidx.test:rules:${Versions.atsl}"
    // COROUTINE
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    // MOCK WEBSERVER
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
}