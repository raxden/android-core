package com.core.app.base.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.commons.DisposableManager
import com.core.commons.LoaderManager
import javax.inject.Inject
import javax.inject.Named

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var mLoaderManager: LoaderManager
    @Inject
    @field:Named(BaseViewModelModule.DISPOSABLE_VIEWMODEL_MANAGER)
    lateinit var mDisposableManager: DisposableManager

    override fun onCleared() {
        mDisposableManager.dispose()
        super.onCleared()
    }

    fun getLoader(): MutableLiveData<Boolean> = mLoaderManager.getLoader()

}