package com.core.app.util

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ErrorManager(val mResources: Resources) {

    private val mCode: MutableLiveData<Long> = MutableLiveData()
    private val mTitle: MutableLiveData<String> = MutableLiveData()
    private val mMessage: MutableLiveData<String> = MutableLiveData()

    init {
        mCode.postValue(0)
        mTitle.postValue("")
        mMessage.postValue("")
    }

    fun set(throwable: Throwable) {
//        TODO
    }

    fun set(code: Long, title: Int, message: Int) {
//        TODO
    }

    fun set(code: Long, title: String, message: String) {
        mCode.postValue(code)
        mTitle.postValue(title)
        mMessage.postValue(message)
    }

    fun getCode(): LiveData<Long> = mCode

    fun getTitle(): LiveData<String> = mTitle

    fun getMessage(): LiveData<String> = mMessage
}