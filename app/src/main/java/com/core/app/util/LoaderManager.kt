package com.core.app.util

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class LoaderManager(val mContext: Context) {

    private val mStatus: MutableLiveData<Boolean> = MutableLiveData()
    private val mMessage: MutableLiveData<String> = MutableLiveData()
    private var mCounter: Int = 0

    init {
        mStatus.postValue(false)
        mMessage.postValue("")
    }

    @Synchronized
    fun push(message: Int) {
        mCounter.inc()
        mStatus.postValue(true)
        mMessage.postValue(mContext.resources.getString(message))
    }

    @Synchronized
    fun push(message: String) {
        mCounter.inc()
        mStatus.postValue(true)
        mMessage.postValue(message)
    }

    @Synchronized
    fun pop() {
        if (mCounter > 0) mCounter.dec()
        else mStatus.postValue(false)
    }

    @Synchronized
    fun clear() {
        mCounter = 0
        mStatus.postValue(false)
    }

    fun getStatus(): LiveData<Boolean> = mStatus

    fun getMessage(): LiveData<String> = mMessage

}