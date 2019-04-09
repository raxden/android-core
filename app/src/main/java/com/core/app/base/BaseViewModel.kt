package com.core.app.base

import androidx.lifecycle.ViewModel
import com.core.app.util.ErrorManager
import com.core.app.util.LoaderManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var mLoaderManager: LoaderManager
    @Inject
    lateinit var mErrorManager: ErrorManager
    @Inject
    lateinit var mCompositeDisposable: CompositeDisposable

    /**
     * This method will be called when this ViewModel is created.
     */
    open fun onCreated() {}

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getLoader(): LoaderManager = mLoaderManager

    fun getError(): ErrorManager = mErrorManager
}