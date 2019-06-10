package com.core.app.base

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import com.core.app.util.ErrorManager
import com.core.app.util.LoaderManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var resources: Resources
    @Inject
    lateinit var loaderManager: LoaderManager
    @Inject
    lateinit var errorManager: ErrorManager

    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * This method will be called when this ViewModel is created.
     */
    open fun onAttached() {}

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getLoader(): LoaderManager = loaderManager

    fun getError(): ErrorManager = errorManager
}