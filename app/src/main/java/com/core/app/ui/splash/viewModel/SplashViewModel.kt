package com.core.app.ui.splash.viewModel

import com.core.app.base.mvvm.BaseViewModel
import io.reactivex.Completable
import io.reactivex.Single
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
                        mLoaderManager.push()
                    }

                    override fun onComplete() {
                        mLoaderManager.pull()
                    }

                    override fun onError(e: Throwable) {
                        mLoaderManager.pull()
                    }
                }))
    }

    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}