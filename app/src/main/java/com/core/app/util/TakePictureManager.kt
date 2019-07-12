package com.core.app.util

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.tbruyelle.rxpermissions2.Permission
import io.reactivex.Single

class TakePictureManager(
        val fragmentManager: FragmentManager,
        val permissionManager: PermissionManager
) {

    constructor(activity: FragmentActivity, permissionManager: PermissionManager) : this(activity.supportFragmentManager, permissionManager)

    constructor(fragment: Fragment, permissionManager: PermissionManager) : this(fragment.childFragmentManager, permissionManager)

    enum class Type { ALL, CAMERA, GALLERY }

    fun take(type: Type, chooserTitle: String? = null): Single<Uri> = Single.create { emitter ->
        permissionManager.requestPermission(object : PermissionManager.Callback {
            override fun onPermissionGranted(permission: Permission) {
                initFragment(type, chooserTitle, object : TakePictureFragment.Callback {
                    override fun onPictureRetrieved(picture: Uri) {
                        emitter.onSuccess(picture)
                    }
                })
            }

            override fun onPermissionDenied(permission: Permission) {
                emitter.onError(RuntimeException("Permission denied"))
            }
        }, Manifest.permission.CAMERA)
    }

    private fun initFragment(type: Type, chooserTitle: String?, callback: TakePictureFragment.Callback): TakePictureFragment {
        var fragment = fragmentManager.findFragmentByTag(TakePictureFragment::class.java.simpleName) as? TakePictureFragment
        if (fragment == null) {
            fragment = TakePictureFragment.newInstance(type, chooserTitle, callback)
            fragmentManager.beginTransaction().add(fragment, TakePictureFragment::class.java.simpleName).commitNow()
        } else {
            fragment.type = type
            fragment.chooserTitle = chooserTitle
            fragment.callback = callback
            fragment.takePicture()
        }
        return fragment
    }

    private class TakePictureFragment(
            var type: Type,
            var chooserTitle: String?,
            var callback: Callback
    ) : Fragment() {

        interface Callback {
            fun onPictureRetrieved(picture: Uri)
        }

        companion object {
            private const val REQUEST_IMAGE_CAPTURE = 10023

            fun newInstance(type: Type, chooserTitle: String?, callback: Callback) = TakePictureFragment(type, chooserTitle, callback)
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            activity?.run {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    ImagePickerUtil.getUriFromResult(this, resultCode, data)?.also {
                        callback.onPictureRetrieved(it)
                    }
                }
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            retainInstance = true

            takePicture()
        }

        fun takePicture() {
            activity?.also { activity ->
                when (type) {
                    Type.ALL -> ImagePickerUtil.getPickImageIntent(activity, chooserTitle)
                    Type.CAMERA -> ImagePickerUtil.getPickCameraImageIntent(activity)
                    Type.GALLERY -> ImagePickerUtil.getPickGalleryImageIntent()
                }.also { intent ->
                    intent.resolveActivity(activity.packageManager)?.run {
                        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                    }
                }
            }
        }
    }
}