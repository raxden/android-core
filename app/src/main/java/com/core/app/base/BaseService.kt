package com.core.app.base

import android.app.Service
import dagger.android.AndroidInjection

abstract class BaseService : Service() {

//    @Inject
//    @field:Named(DISPOSABLE_SERVICE_MANAGER)
//    lateinit var mDisposableManager: DisposableManager

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
//        mDisposableManager.dispose()
    }

}
