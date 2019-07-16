package com.core.app.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.R
import com.core.app.base.BaseViewModel
import com.core.commons.extension.subscribeWith
import com.core.domain.User
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val getVersionUseCase: GetVersionUseCase,
        private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val mVersion = MutableLiveData<String>()
    val version: LiveData<String> = mVersion

    private val mUsernameError = MutableLiveData<Int>()
    val usernameError: LiveData<Int> = mUsernameError

    private val mUserLogged = MutableLiveData<User>()
    val userLogged: LiveData<User> = mUserLogged

    val username = MutableLiveData<String>()

    init {
        retrieveVersion()
    }

    fun onUsernameChanged() {
        mUsernameError.value = 0
    }

    fun performLogin() {
        if (!validateUsername()) return
        username.value?.let { performLogin(it) }
    }

    private fun retrieveVersion() {
        getVersionUseCase.execute()
                .subscribeWith(onSuccess = { mVersion.value = it })
                .addTo(mCompositeDisposable)
    }

    private fun performLogin(username: String) {
        loginUseCase.execute(username)
                .subscribeWith(
                        onStart = {
                            mLoaderManager.push()
                        },
                        onError = {
                            mThrowable.value = it
                            mLoaderManager.pop()
                        },
                        onSuccess = {
                            mUserLogged.value = it
                            mLoaderManager.pop()
                        }
                )
                .addTo(mCompositeDisposable)
    }

    private fun validateUsername(): Boolean {
        val username = username.value ?: ""
        return if (username.isEmpty()) {
            mUsernameError.value = R.string.enter_username
            false
        } else true
    }
}