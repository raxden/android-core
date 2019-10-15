package com.core.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.app.util.LoaderManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val mLoaderManager: LoaderManager = LoaderManager()
    val loader: LoaderManager = mLoaderManager

    protected val mThrowable: MutableLiveData<Throwable> = MutableLiveData()
    val throwable: LiveData<Throwable> = mThrowable

    protected val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * This method will be called when this ViewModel is created.
     */
    open fun onAttached() {}

    override fun onCleared() {
        mLoaderManager.clear()
        mCompositeDisposable.clear()
        super.onCleared()
    }
}