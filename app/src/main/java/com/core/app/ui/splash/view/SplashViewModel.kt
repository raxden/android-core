package com.core.app.ui.splash.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.extension.subscribeWith
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        const val IN_MILLISECONDS: Long = 3000
    }

    private val mApplicationReady: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreated() {
        makeTime()
                .subscribeWith(
                        onStart = { mLoaderManager.push("preparing application to launch...") },
                        onError = {
                            mLoaderManager.pop()
                            mErrorManager.set(it)
                        },
                        onComplete = {
                            mApplicationReady.value = true
                        }
                )
                .addTo(mCompositeDisposable)
    }

    fun isApplicationReadyToLaunch(): LiveData<Boolean> = mApplicationReady

    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}