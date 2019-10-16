package com.core.app.ui.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.core.app.R
import com.core.app.base.BaseViewModel
import com.core.app.util.OpenForTesting
import com.core.commons.Status
import com.core.domain.User
import com.core.domain.interactor.GetVersionUseCase
import com.core.domain.interactor.LoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@OpenForTesting
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
        viewModelScope.launch {
            val resource = getVersionUseCase.execute()
            when (resource.status) {
                Status.SUCCESS -> mVersion.value = resource.data
            }
        }
    }

    private fun performLogin(username: String) {
        viewModelScope.launch {
            mLoaderManager.push()
            val resource = loginUseCase.execute(username)
            when (resource.status) {
                Status.ERROR -> mThrowable.value = resource.throwable
                Status.SUCCESS -> mUserLogged.value = resource.data
            }
            mLoaderManager.pop()
        }
    }

    private fun validateUsername(): Boolean {
        val username = username.value ?: ""
        return if (username.isEmpty()) {
            mUsernameError.value = R.string.enter_username
            false
        } else true
    }
}