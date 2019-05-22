package com.core.app.ui.screens.login.view

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.core.app.base.BaseViewModel
import com.core.commons.extension.subscribeWith
import com.core.domain.Account
import com.core.domain.interactor.LoginUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    val username: MutableLiveData<String> = MutableLiveData()
    val usernameError: MutableLiveData<String> = MutableLiveData()
    val userLogged: MutableLiveData<Account> = MutableLiveData()

    private var mValidForm = false

    fun onLoginClicked() {
        mValidForm = validateUsername()
        if (mValidForm) performLogin(username.value ?: "")
    }

    fun onUsernameChanged() {
        usernameError.postValue("")
    }

    private fun performLogin(username: String) {
        loginUseCase.execute(username)
                .subscribeWith(
                        onStart = {
                            loaderManager.push("validando credenciales...")
                        },
                        onError = {
                            loaderManager.pop()
                            errorManager.set(it)
                        },
                        onSuccess = {
                            userLogged.postValue(it)
                        }
                )
                .addTo(compositeDisposable)
    }

    private fun validateUsername(): Boolean {
        if (TextUtils.isEmpty(username.value)) {
            usernameError.postValue("El campo username no puede estar vacio")
            return false
        } else usernameError.postValue("")
        return true
    }
}