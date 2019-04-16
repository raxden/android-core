package com.core.app.ui.screens.login.view

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.ValidationHelper
import com.core.commons.extension.subscribeWith
import com.core.domain.User
import com.core.domain.interactor.LoginUseCase
import io.reactivex.Completable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()
    val userLogged: MutableLiveData<User> = MutableLiveData()

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
        loginUseCase.execute(username, password)
                .subscribeWith(
                        onStart = { mLoaderManager.push("validando credenciales...") },
                        onError = {
                            mLoaderManager.pop()
                            mErrorManager.set(it)
                        },
                        onSuccess = {
                            userLogged.postValue(it)
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