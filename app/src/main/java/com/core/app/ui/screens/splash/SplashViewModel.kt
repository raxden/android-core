package com.core.app.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.core.app.base.BaseViewModel
import com.core.app.util.OpenForTesting
import com.core.commons.Status
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
import kotlinx.coroutines.*

@OpenForTesting
class SplashViewModel @Inject constructor(
        private val getVersionUseCase: GetVersionUseCase,
        private val forwardUseCase: ForwardUseCase
) : BaseViewModel() {

    private val mVersion = MutableLiveData<String>()
    val version: LiveData<String> = mVersion

    private val mApplicationReady = MutableLiveData<Pair<Forward, User?>>()
    val applicationReady: LiveData<Pair<Forward, User?>> = mApplicationReady

    init {
        retrieveVersion()
        prepareApplicationToLaunch()
    }

    private fun retrieveVersion() {
        viewModelScope.launch {
            val resource = getVersionUseCase.execute()
            when (resource.status) {
                Status.SUCCESS -> mVersion.value = resource.data
            }
        }
    }

    private fun prepareApplicationToLaunch() {
        viewModelScope.launch {
            val resource = forwardUseCase.execute()
            when (resource.status) {
                Status.SUCCESS -> mApplicationReady.value = forwardUseCase.execute().data
            }
        }
    }
}