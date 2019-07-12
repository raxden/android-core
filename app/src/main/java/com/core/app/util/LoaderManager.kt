package com.core.app.util

import androidx.lifecycle.MutableLiveData
import timber.log.Timber

class LoaderManager {

    val status: MutableLiveData<Boolean> = MutableLiveData()
    val message: MutableLiveData<Int> = MutableLiveData()
    private var counter: Int = 0

    init {
        status.postValue(false)
        message.postValue(0)
    }

    @Synchronized
    fun push() {
        counter = counter.inc()
        Timber.d("[push]counter: %s", counter)
        status.postValue(true)
    }

    @Synchronized
    fun push(message: Int) {
        counter = counter.inc()
        Timber.d("[push]counter: %s", counter)
        status.postValue(true)
        this.message.postValue(message)
    }

    @Synchronized
    fun pop() {
        if (counter > 0) counter = counter.dec()
        Timber.d("[pop]counter: %s", counter)
        if (counter == 0) status.postValue(false)
    }

    @Synchronized
    fun clear() {
        counter = 0
        status.postValue(false)
    }
}