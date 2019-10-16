package com.core.app.ui.screens.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.core.app.base.BaseViewModel
import com.core.app.util.OpenForTesting
import com.core.commons.Resource
import com.core.domain.User
import com.core.domain.interactor.GetUserUseCase
import com.core.domain.interactor.GetVersionUseCase
import javax.inject.Inject

@OpenForTesting
class SplashViewModel @Inject constructor(
        private val getVersionUseCase: GetVersionUseCase,
        private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    val version: LiveData<Resource<String>> = liveData {
        emit(Resource.loading())
        emit(getVersionUseCase.execute())
    }

    val user: LiveData<Resource<User>> = liveData {
        emit(Resource.loading())
        emit(getUserUseCase.execute())
    }
}