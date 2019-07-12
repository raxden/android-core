package com.core.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.core.app.util.LoaderManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val mThrowable: MutableLiveData<Throwable> = MutableLiveData()
    protected val mLoaderManager: LoaderManager = LoaderManager()
    protected val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    val loader: LoaderManager
        get() = mLoaderManager

    val throwable: LiveData<Throwable>
        get() = mThrowable

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