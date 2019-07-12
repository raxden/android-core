# How is that

This project is created like a template to start another projets. It`s based in MVVM pattern with LiveData and Data Binding, besides use Dagger2 to injection dependencies, RX2 to reactive programing and Retrofit2 to HTTP layer.
    
### Project structure    
    
### Flavours

### Libraries used in project

    ConstraintLayout        - https://developer.android.com/reference/android/support/constraint/ConstraintLayout
    Material Components     - https://github.com/material-components/material-components-android
    Kotlin
    Rx2                     - https://github.com/ReactiveX/RxKotlin
    RxPermissions           - https://github.com/tbruyelle/RxPermissions
    Room                    - https://developer.android.com/jetpack/androidx/releases/room
    ThreeTenVersion         - https://github.com/JakeWharton/ThreeTenABP
    Crashlytics             - https://fabric.io/kits/android/crashlytics/install
    Gson                    - https://github.com/google/gson
    Dagger                  - https://github.com/google/dagger
    Glide                   - https://github.com/bumptech/glide
    OkHttp                  - https://github.com/square/okhttp
    Retrofit                - https://github.com/square/retrofit
    Timber                  - https://github.com/JakeWharton/timber

### Steps to configure project

    1. Rename androidApplicationId and testApplicationId in /build.gradle.
    
        com.core.app -> com.yourcompanyname.yourprojectname

    2. To use Firebase in project, register application in Firebase and download|replace google-services.json contained in
    app module. That proyect has n(flavours) diferences configurations. Therefore you must register n(flavours) 
    applications instead of one.

        {androidApplicationId}
        {androidApplicationId}.dev
        {androidApplicationId}.mock

    3. Create|Replace release.jks keystore

        buildSystem/release.jks

    6. To use Crashlytics, rename fabric.properties.template -> fabric.properties To personalize configuration like distribution or
    release notes, modify preferences in build.gradle.
    
    8. Defines path to store pictures in device in: (Replace package com.core.app by yours)

        dev/res/xml/provider_paths.xml
        prod/res/xml/provider_paths.xml

### Tips

    1. All project configuration is located in build.gradle
    2. Library dependency configuration is located in buildSystem/dependencies.gradle.
    3. keystore's is located in buildSystem/*


