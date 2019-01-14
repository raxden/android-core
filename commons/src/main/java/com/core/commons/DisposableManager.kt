package com.core.commons

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Ángel Gómez on 23/02/2018.
 */
class DisposableManager {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun dispose() {
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun remove(disposable: Disposable) {
        if (!disposable.isDisposed) disposable.dispose()
        compositeDisposable.remove(disposable)
    }

    fun removeAll() {
        compositeDisposable.clear()
    }

}
