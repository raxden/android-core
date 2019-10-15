package com.core.app.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.app.util.OpenForTesting
import com.core.domain.Forward
import com.core.domain.User
import com.core.domain.interactor.ForwardUseCase
import com.core.domain.interactor.GetVersionUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@OpenForTesting
class SplashViewModel @Inject constructor(
        private val getVersionUseCase: GetVersionUseCase,
        private val forwardUseCase: ForwardUseCase
) : BaseViewModel() {

    companion object {
        const val IN_SECONDS: Long = 3
    }

    private val mVersion = MutableLiveData<String>()
    val version: LiveData<String> = mVersion

    private val mApplicationReady = MutableLiveData<Pair<Forward, User?>>()
    val applicationReady: LiveData<Pair<Forward, User?>> = mApplicationReady

    init {
        retrieveVersion()
        prepareApplicationToLaunch()
    }

    private fun retrieveVersion() {
        getVersionUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = { mVersion.value = it })
                .addTo(mCompositeDisposable)
    }

    private fun prepareApplicationToLaunch() {
        Single.zip(
                Single.timer(IN_SECONDS, TimeUnit.SECONDS, Schedulers.io()),
                forwardUseCase.execute().subscribeOn(Schedulers.io()),
                BiFunction<Long, Pair<Forward, User?>, Pair<Forward, User?>> { _, forward -> forward })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = { mApplicationReady.value = it } )
                .addTo(mCompositeDisposable)
    }
}