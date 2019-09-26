package com.core.commons.extension

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.notifyObservers() {
    this.value = this.value
}