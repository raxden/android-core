============ HOW IS THAT ===============================================

    This project is created like a template to start another projets. 
    It`s based in MVVM pattern with LiveData and Data Binding, besides use Dagger2 to injection dependencies, 
    RX2 to reactive programing and Retrofit2 to HTTP layer.
    
    Another libraries used in project:

    - Timber - Library to print logs
    - Glide - Library to load images.
    - Crashlytics - Library to register any crash in app.
    - ThreeTenABP - Library to use dates

============ STEPS TO CONFIGURE PROJECT ======================================

    1. Rename androidApplicationId and testApplicationId in build.gradle.
        com.core.app -> com.yourcompanyname.yourprojectname

    2. Register application in Firebase and download|replace google-services.json contained in
    app module. Remember that proyect has n(flavours) diferences configurations, dev and prod.
    Therefore you must register n(flavours) applications instead of one.
        {androidApplicationId}
        {androidApplicationId}.dev
        {androidApplicationId}.mock

    3. Create|Replace release.jks keystore
        buildSystem/release.jks

    6. To use Crashlytics, rename fabric.properties.template -> fabric.properties
    To personalize configuration like distribution or release notes, modify preferences in build.gradle.

    7. Define what languages will be use the application in
    com.omvp.app.injector.module.LocaleModule

    8. Defines path to store pictures in device in: (Replace package com.core.app by yours)
        + dev/res/xml/provider_paths.xml
        + prod/res/xml/provider_paths.xml

============================================= TIPS =================================================

    1. All project configuration is located in build.gradle
    2. Library dependency configuration is located in buildSystem/dependencies.gradle.
    3. keystore's is located in buildSystem/*


