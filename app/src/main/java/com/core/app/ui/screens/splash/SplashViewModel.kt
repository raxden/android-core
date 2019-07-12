package com.core.app.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.Event
import com.core.commons.extension.subscribeWith
import com.core.domain.Forward
import com.core.domain.User
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {

    companion object {
        const val IN_SECONDS: Long = 3
    }

    private val mApplicationReady = MutableLiveData<Event<Pair<Forward, User?>>>()

    val applicationReady: LiveData<Event<Pair<Forward, User?>>>
        get() = mApplicationReady

    init {

    }

//    override fun onAttached() {
//        makeTime()
//                .subscribeWith(
//                        onStart = {
//                            loaderManager.push()
//                        },
//                        onError = {
//                            loaderManager.pop()
//                            mThrowable.value = it
//                        },
//                        onComplete = {
//                            applicationReady.value = true
//                        }
//                )
//                .addTo(compositeDisposable)
//    }
//
//    fun isApplicationReadyToLaunch(): LiveData<Boolean> = applicationReady
//
//    private fun makeTime(): Completable = Completable.timer(IN_MILLISECONDS, TimeUnit.MILLISECONDS)

}