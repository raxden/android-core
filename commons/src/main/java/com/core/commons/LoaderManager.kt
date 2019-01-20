package com.core.commons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoaderManager {

    private val loader: MutableLiveData<Boolean> = MutableLiveData()
    private var counter: Int = 0

    @Synchronized
    fun push() {
        counter.inc()
        loader.postValue(true)
    }

    @Synchronized
    fun pull() {
        if (counter > 0) counter.dec()
        else loader.postValue(false)
    }

    @Synchronized
    fun clear() {
        counter = 0
        loader.postValue(false)
    }

    fun getLoader(): LiveData<Boolean> = loader

}