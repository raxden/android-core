package com.core.app.base

import android.app.Service
import com.core.app.base.BaseServiceModule.Companion.DISPOSABLE_SERVICE_MANAGER
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

abstract class BaseService : Service() {

    @Inject
    @field:Named(DISPOSABLE_SERVICE_MANAGER)
    lateinit var mCompositeDisposable: CompositeDisposable

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }
}
