package com.core.app.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelManager(
        private val activity: AppCompatActivity,
        private val viewModelFactory: ViewModelProvider.Factory
) {

    fun <VM: ViewModel> load(viewModelClass: Class<VM>): VM {
        return ViewModelProvider(activity, viewModelFactory).get(viewModelClass)
    }
}