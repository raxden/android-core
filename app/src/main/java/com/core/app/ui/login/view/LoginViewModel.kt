package com.core.app.ui.login.view

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.app.base.mvvm.BaseViewModel
import com.core.commons.ValidationHelper
import com.core.commons.extension.subscribeWith
import com.core.domain.interactor.LoginUseCase
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val mLoginUseCase: LoginUseCase
) : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()
    val userLogged: MutableLiveData<Boolean> = MutableLiveData()

    private var mValidForm = false

    fun onLoginClicked() {
        mValidForm = validateUsername()
        mValidForm = validatePassword()
        if (mValidForm) performLogin(username.value ?: "", password.value ?: "")
    }

    fun onUsernameChanged() {
        usernameError.postValue("")
    }

    fun onPasswordChanged() {
        passwordError.postValue("")
    }

    private fun performLogin(username: String, password: String) {
        mLoginUseCase.execute(username, password)
                .flatMapCompletable { Completable.complete() }
                .subscribeWith(
                        onStart = { mLoaderManager.push("validando credenciales...") },
                        onError = {
                            mLoaderManager.pop()
                            mErrorManager.set(it)
                        },
                        onComplete = {
                            userLogged.postValue(true)
                        }
                )
                .addTo(mCompositeDisposable)
    }

    private fun validateUsername(): Boolean {
        if (TextUtils.isEmpty(username.value)) {
            usernameError.postValue("El campo username no puede estar vacio")
            return false
        } else usernameError.postValue("")
        return true
    }

    private fun validatePassword(): Boolean {
        if (TextUtils.isEmpty(password.value ?: "")) {
            passwordError.postValue("El campo password no puede estar vacio")
            return false
        } else passwordError.postValue("")
        if (!ValidationHelper.isPassword(password.value ?: "")) {
            passwordError.postValue("Contraseña con formato incorrecto")
            return false
        } else passwordError.postValue("")
        return true
    }

}