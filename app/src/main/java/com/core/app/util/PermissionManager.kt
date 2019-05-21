package com.core.app.util

import android.Manifest
import android.content.Context
import com.core.app.R
import com.core.commons.extension.subscribeWith
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

class PermissionManager(
        private val context: Context,
        private val permissions: RxPermissions,
        private val compositeDisposable: CompositeDisposable) {

    interface Callback {
        fun onPermissionGranted(permission: Permission)
        fun onPermissionDenied(permission: Permission)
    }

    private var requestingPermission: Boolean = false

    fun hasPermission(permission: String): Boolean = permissions.isGranted(permission)

    fun requestPermission(callback: Callback, vararg permissions: String) {
        if (requestingPermission) return
        this.permissions.requestEach(*permissions)
                .subscribeWith(
                        onStart = { requestingPermission = true },
                        onNext = { handlePermission(it, callback) },
                        onError = { Timber.e(it) },
                        onComplete = { requestingPermission = false }
                )
                .addTo(compositeDisposable)
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
                showRequestPermissionRationale(R.string.permission_rationale_access_fine_location_title, R.string.permission_rationale_access_fine_location_message, R.string.permission_rationale_access_fine_location_positive, R.string.permission_rationale_access_fine_location_negative, permission, callback)
            Manifest.permission.CAMERA ->
                showRequestPermissionRationale(R.string.permission_rationale_camera_title, R.string.permission_rationale_camera_message, R.string.permission_rationale_camera_positive, R.string.permission_rationale_camera_negative, permission, callback)
        }
    }

    private fun showRequestPermissionRationale(title: Int, message: Int, positive: Int, negative: Int, permission: Permission, callback: Callback) {
        MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { _, _ -> requestPermission(callback, permission.name) }
                .setNegativeButton(negative) { _, _ -> callback.onPermissionDenied(permission) }
                .create()
                .show()
    }
}