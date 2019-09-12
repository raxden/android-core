package com.core.app.util

import androidx.appcompat.app.AppCompatActivity
import com.core.app.BuildConfig
import com.core.app.R
import com.core.data.network.gateway.retrofit.exception.RetrofitException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.exceptions.CompositeException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorManager(
        private val activity: AppCompatActivity
) {

    private val mErrorList = mutableSetOf<BundleError>()

    fun set(throwable: Throwable) {
        var code = 0
        var title: String = activity.getString(R.string.unespected_error_title)
        var message: String = activity.getString(R.string.unespected_error_message)
        when (throwable) {
            is CompositeException -> for (childThrowable in throwable.exceptions) set(childThrowable)
            is RetrofitException -> {
                code = throwable.response?.code() ?: 0
                message = if (BuildConfig.DEBUG) throwable.cause?.message ?: message
                else when (throwable.cause) {
                    is ConnectException -> activity.getString(R.string.unespected_timeout_message)
                    is SocketTimeoutException -> activity.getString(R.string.unespected_timeout_message)
                    is UnknownHostException -> activity.getString(R.string.unespected_timeout_message)
                    else -> activity.getString(R.string.unespected_error_message)
                }
            }
            else -> {
                if (BuildConfig.DEBUG) message = throwable.message ?: message
            }
        }
        set(code, title, message)
    }

    fun set(code: Int, title: Int, message: Int) {
        handleError(BundleError(code, activity.getString(title), activity.getString(message)))
    }

    fun set(code: Int, title: String, message: String) {
        handleError(BundleError(code, title, message))
    }

    @Synchronized
    private fun handleError(error: BundleError) {
        if (mErrorList.isEmpty()) {
            mErrorList.add(error)
            showError(error)
        } else mErrorList.add(error)
    }

    @Synchronized
    private fun showError(error: BundleError) {
        MaterialAlertDialogBuilder(activity)
                .setTitle(error.title)
                .setMessage(error.message)
                .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                .setOnDismissListener {
                    mErrorList.remove(mErrorList.first())
                    if (mErrorList.isNotEmpty()) showError(mErrorList.first())
                }
                .create()
                .show()
    }

    private class BundleError(val code: Int, val title: String, val message: String)

}