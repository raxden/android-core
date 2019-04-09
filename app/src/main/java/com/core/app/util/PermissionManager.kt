package com.core.app.util

import android.Manifest
import android.app.Activity
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.core.commons.extension.subscribeWith
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class PermissionManager(
        private val mActivity: Activity,
        private val mRxPermissions: RxPermissions,
        private val mCompositeDisposable: CompositeDisposable) {

    interface Callback {
        fun onPermissionGranted(permission: Permission)
        fun onPermissionDenied(permission: Permission)
    }

    private var mRequestingPermission: Boolean = false

    fun hasPermission(permission: String): Boolean = mRxPermissions.isGranted(permission)

    fun requestPermission(callback: Callback, vararg permissions: String) {
        if (mRequestingPermission) return
        mRxPermissions.requestEach(*permissions)
                .subscribeWith(
                        onStart = { mRequestingPermission = true },
                        onNext = { handlePermission(it, callback) },
                        onError = { Timber.e(it) },
                        onComplete = { mRequestingPermission = false }
                )
                .addTo(mCompositeDisposable)
    }

    private fun handlePermission(permission: Permission, callback: Callback) {
        when {
            permission.granted -> callback.onPermissionGranted(permission)
            permission.shouldShowRequestPermissionRationale -> handleRequestPermissionRationale(permission, callback)
            else -> callback.onPermissionDenied(permission)
        }
    }

    private fun handleRequestPermissionRationale(permission: Permission, callback: Callback) {
        when (permission.name) {
            Manifest.permission.ACCESS_FINE_LOCATION ->
                showRequestPermissionRationale("", "", "", "", permission, callback)
            Manifest.permission.CAMERA ->
                showRequestPermissionRationale("", "", "", "", permission, callback)
        }
    }

    private fun showRequestPermissionRationale(title: String, message: String, positive: String, negative: String, permission: Permission, callback: Callback) {
        AlertDialog.Builder(mActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { _, _ -> requestPermission(callback, permission.name) }
                .setNegativeButton(negative) { _, _ -> callback.onPermissionDenied(permission) }
                .create()
                .show()
    }
}