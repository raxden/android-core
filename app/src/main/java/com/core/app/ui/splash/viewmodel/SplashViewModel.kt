package com.core.app.ui.splash.viewmodel

import com.core.app.base.mvvm.BaseViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(): BaseViewModel() {

    companion object {
        const val IN_MILLISECONDS: Long = 3000
    }

    fun prepareApplicationToLaunch() {
        mDisposableManager.add(makeTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableCompletableObserver() {
                    override fun onStart() {
                        mLoaderManager.push("preparing application to launch...")
                    }

                    override fun onComplete() {
                        mLoaderManager.pop()
                    }

                    override fun onError(e: Throwable) {
                        mLoaderManager.pop()
                    }
                }))
    }

    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}