object ApplicationId {
    val id = "com.core.app"
}

object Modules {
    val app = ":app"

    val navigation = ":navigation"

    val common = ":common"

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
}

object KotlinLibraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinCoroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
}