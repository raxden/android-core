package com.core.app.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.Event
import com.core.commons.extension.subscribeWith
import com.core.domain.Forward
import com.core.domain.User
import com.core.domain.interactor.ForwardUseCase
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
        private val forwardUseCase: ForwardUseCase
) : BaseViewModel() {

    companion object {
        const val IN_SECONDS: Long = 3
    }

    val applicationReady: LiveData<Pair<Forward, User?>>
        get() = MutableLiveData<Pair<Forward, User?>>().apply {
            Single.zip(
                    Single.timer(IN_SECONDS, TimeUnit.SECONDS, Schedulers.io()),
                    forwardUseCase.execute().subscribeOn(Schedulers.io()),
                    BiFunction<Long, Pair<Forward, User?>, Pair<Forward, User?>> { _, forward -> forward })
                    .subscribeWith(
                            onSuccess = { value = it },
                            onError = { value = Pair(Forward.LOGIN, null) }
                    )
                    .addTo(compositeDisposable)
        }
}