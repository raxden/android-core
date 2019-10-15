package com.core.app.base

import android.app.Service
import com.core.app.base.service.BaseServiceModule.Companion.DISPOSABLE_SERVICE_MANAGER
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

abstract class BaseService : Service() {

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }
}
