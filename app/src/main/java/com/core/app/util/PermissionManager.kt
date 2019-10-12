package com.core.app.util

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import com.core.app.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PermissionManager(
        private val activity: AppCompatActivity,
        private val rxPermissions: RxPermissions,
        private val compositeDisposable: CompositeDisposable
) {

    interface Callback {
        fun onPermissionGranted(permission: Permission)
        fun onPermissionDenied(permission: Permission)
    }

    private var requestingPermission: Boolean = false

    fun hasPermission(permission: String): Boolean = rxPermissions.isGranted(permission)

    fun requestPermission(callback: Callback, vararg permissions: String) {
        if (requestingPermission) return
        this.rxPermissions.requestEach(*permissions)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { requestingPermission = true }
                .subscribeBy(
                        onNext = { handlePermission(it, callback) },
                        onError = {
                            requestingPermission = false
                            Timber.e(it)
                        },
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
        MaterialAlertDialogBuilder(activity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive) { _, _ -> requestPermission(callback, permission.name) }
                .setNegativeButton(negative) { _, _ -> callback.onPermissionDenied(permission) }
                .create()
                .show()
    }
}