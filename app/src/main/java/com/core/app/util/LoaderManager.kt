package com.core.app.util

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoaderManager(private val resources: Resources) {

    private val status: MutableLiveData<Boolean> = MutableLiveData()
    private val message: MutableLiveData<String> = MutableLiveData()
    private var counter: Int = 0

    init {
        status.postValue(false)
        message.postValue("")
    }

    @Synchronized
    fun push() {
        counter = counter.inc()
        status.postValue(true)
    }

    @Synchronized
    fun push(message: Int) {
        counter = counter.inc()
        status.postValue(true)
        this.message.postValue(resources.getString(message))
    }

    @Synchronized
    fun push(message: String) {
        counter = counter.inc()
        status.postValue(true)
        this.message.postValue(message)
    }

    @Synchronized
    fun pop() {
        if (counter > 0) counter = counter.dec()
        if (counter == 0) status.postValue(false)
    }

    @Synchronized
    fun clear() {
        counter = 0
        status.postValue(false)
    }

    fun getStatus(): LiveData<Boolean> = status

    fun getMessage(): LiveData<String> = message

}