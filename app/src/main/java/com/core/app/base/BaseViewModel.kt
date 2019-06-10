package com.core.app.base

import androidx.lifecycle.ViewModel
import com.core.app.util.ErrorManager
import com.core.app.util.LoaderManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var loaderManager: LoaderManager
    @Inject
    lateinit var errorManager: ErrorManager

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * This method will be called when this ViewModel is created.
     */
    open fun onCreated() {}

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getLoader(): LoaderManager = loaderManager

    fun getError(): ErrorManager = errorManager
}