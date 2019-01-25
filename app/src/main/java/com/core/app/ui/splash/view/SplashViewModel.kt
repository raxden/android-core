package com.core.app.ui.splash.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val mApplicationReady: MutableLiveData<Boolean> = MutableLiveData()

    init {
        mCompositeDisposable.add(makeTime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableCompletableObserver() {
                    override fun onStart() {
                        mLoaderManager.push("preparing application to launch...")
                    }

                    override fun onComplete() {
                        mApplicationReady.value = true
                        mLoaderManager.pop()
                    }

                    override fun onError(e: Throwable) {
                        mLoaderManager.pop()
                    }
                }))
    }

    fun isApplicationReadyToLaunch() : LiveData<Boolean> = mApplicationReady

    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}