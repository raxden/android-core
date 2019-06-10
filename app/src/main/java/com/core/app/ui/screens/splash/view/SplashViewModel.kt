package com.core.app.ui.screens.splash.view

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

    private val applicationReady: MutableLiveData<Boolean> = MutableLiveData()

    override fun onAttached() {
        makeTime()
                .subscribeWith(
                        onStart = { loaderManager.push("preparing application to launch...") },
                        onError = {
                            loaderManager.pop()
                            errorManager.set(it)
                        },
                        onComplete = {
                            applicationReady.value = true
                        }
                )
                .addTo(compositeDisposable)
    }

    fun isApplicationReadyToLaunch(): LiveData<Boolean> = applicationReady

    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}