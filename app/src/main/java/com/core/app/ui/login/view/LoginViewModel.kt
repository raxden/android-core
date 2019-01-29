package com.core.app.ui.login.view

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.core.app.base.mvvm.BaseViewModel
import com.core.commons.ValidationHelper
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val passwordError: MutableLiveData<String> = MutableLiveData()

    private var mValidForm = false

    fun onLoginClicked() {
        mValidForm = validateUsername()
        mValidForm = validatePassword()
        if (mValidForm) performLogin()
    }

    fun onUsernameChanged() {
        usernameError.postValue("")
    }

    fun onPasswordChanged() {
        passwordError.postValue("")
    }

    private fun performLogin() {
        mLoaderManager.push("validando credenciales...")
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