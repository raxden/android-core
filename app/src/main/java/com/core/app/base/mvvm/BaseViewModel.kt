package com.core.app.base.mvvm

import androidx.lifecycle.ViewModel
import com.core.app.base.BaseFragmentModule
import com.core.app.base.BaseFragmentModule.Companion.FRAGMENT_COMPOSITE_DISPOSABLE
import com.core.commons.LoaderManager
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

abstract class BaseViewModel : ViewModel() {

    protected var mLoaderManager: LoaderManager = LoaderManager()
    protected var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getLoader(): LoaderManager = mLoaderManager
}