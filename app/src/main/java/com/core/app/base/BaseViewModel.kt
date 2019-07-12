package com.core.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.app.util.LoaderManager
import com.core.commons.Event
import com.core.domain.User
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val mThrowable: MutableLiveData<Throwable> = MutableLiveData()
    protected val loaderManager: LoaderManager = LoaderManager()
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val throwable: LiveData<Throwable>
        get() = mThrowable

    /**
     * This method will be called when this ViewModel is created.
     */
    open fun onAttached() {}

    override fun onCleared() {
        loaderManager.clear()
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getLoader(): LoaderManager = loaderManager
}