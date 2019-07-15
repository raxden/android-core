# How is that

This project is created like a template to start another projets. It`s based in MVVM pattern with LiveData and Data Binding, besides use Dagger2 to injection dependencies, RX2 to reactive programing and Retrofit2 with OkHttp3 to HTTP layer.
    
### Architecture


    
### Project structure    

    app
    data 
    domain
    
    commons
    translations

#### app - Presentation layer

##### MVVM with Data binding

#### domain - Business logic layer

##### Use cases

#### data - Data access layer

##### Repository pattern

#### Module dependency

    app     -> commons
            -> domain
            -> data
    
    domain  -> commons
    
    data    -> commons
            -> domain
    
    commons -> no dependencies

### Flavours

    mock
    dev
    prod

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

All library dependencies configuration is located in buildSystem/dependencies.gradle.

### Steps to configure project

    1. Rename androidApplicationId and testApplicationId in /build.gradle.
    
        com.core.app -> com.yourcompanyname.yourprojectname

    2. To use Firebase in project, register application in Firebase and download|replace google-services.json 
    contained in app module. That proyect has n(flavours) diferences configurations. Therefore you must 
    register n(flavours) applications instead of one. For example:

        {androidApplicationId}
        {androidApplicationId}.dev
        {androidApplicationId}.mock
        
    Once this is done you will have to uncomment this line "apply plugin: 'com.google.gms.google-services'" 
    from app/build.gradle on bottom from file.

    3. To use Crashlytics, enter your keys in fabric.properties contained in app module. In addition you 
    must also apply the fabric plugin uncomment this line "apply plugin: 'io.fabric'" on top from file.

    4. Generate your release.jks keystore through AndroidStudio
    
        buildSystem/release.jks
    
    5. Defines your path to store pictures or files in device: (Replace package com.core.app by yours)

        dev/res/xml/provider_paths.xml
        prod/res/xml/provider_paths.xml

### Tips

### License

    Copyright 2019 Google, Inc.

    Licensed to the Apache Software Foundation (ASF) under one or more contributor
    license agreements. See the NOTICE file distributed with this work for
    additional information regarding copyright ownership. The ASF licenses this
    file to you under the Apache License, Version 2.0 (the "License"); you may not
    use this file except in compliance with the License. You may obtain a copy of
    the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
    WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
    License for the specific language governing permissions and limitations under
    the License.
