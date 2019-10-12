package com.core.app.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import timber.log.Timber

class LoaderManager {

    private val mStatus = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = mStatus

    private val mMessage = MutableLiveData<Int>()
    val message: LiveData<Int> = mMessage

    private var counter: Int = 0

    init {
        mStatus.postValue(false)
        mMessage.postValue(0)
    }

    @Synchronized
    fun push() {
        counter = counter.inc()
        Timber.d("[push]counter: %s", counter)
        mStatus.postValue(true)
    }

    @Synchronized
    fun push(message: Int) {
        counter = counter.inc()
        Timber.d("[push]counter: %s", counter)
        mStatus.postValue(true)
        mMessage.postValue(message)
    }

    @Synchronized
    fun pop() {
        if (counter > 0) counter = counter.dec()
        Timber.d("[pop]counter: %s", counter)
        if (counter == 0) mStatus.postValue(false)
    }

    @Synchronized
    fun clear() {
        counter = 0
        mStatus.postValue(false)
    }
}