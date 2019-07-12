/*
 * Copyright 2014 Ángel Gómez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.core.app.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ExifInterface
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.core.commons.AppUtils
import com.core.commons.BitmapUtils
import com.core.commons.extension.rotate
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 */
object ImagePickerUtil {

    private const val TEMP_IMAGE_NAME = "tempImage"

    fun getPickGalleryImageIntent(): Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

    fun getPickCameraImageIntent(context: Context): Intent {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(context.packageManager) != null) {
            try {
                createImageFile(context).let {
                    saveImagePath(context, it.absolutePath)
                    val authority = AppUtils.getPackageName(context) + ".fileprovider"
                    val photoURI = FileProvider.getUriForFile(context, authority, it)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                }
            } catch (ex: IOException) {
                Timber.e(ex)
            }
        }
        return takePictureIntent
    }

    fun getPickImageIntent(context: Context, chooserTitle: String?): Intent {
        val intentList = mutableListOf<Intent>()
        addIntentToList(context, intentList, getPickGalleryImageIntent())
        addIntentToList(context, intentList, getPickCameraImageIntent(context))
        return Intent.createChooser(intentList.removeAt(intentList.size - 1), chooserTitle
                ?: "").apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toTypedArray<Parcelable>())
        }
    }

    fun getImageFromResult(context: Context, resultCode: Int, intent: Intent): Bitmap? {
        val selectedImage = getUriFromResult(context, resultCode, intent) ?: return null
        return BitmapUtils.decode(context, selectedImage)?.apply {
            rotate(getRotation(context, selectedImage, isCamera(intent)))
        }
    }

    fun getPathFromResult(context: Context, resultCode: Int, intent: Intent): String? {
        return getUriFromResult(context, resultCode, intent)?.path ?: return null
    }

    fun getUriFromResult(context: Context, resultCode: Int, intent: Intent?): Uri? {
        Timber.d("getUriFromResult, resultCode: %d", resultCode)
        if (resultCode == Activity.RESULT_OK) {
            val selectedImage: Uri?
            selectedImage = if (isCamera(intent)) {
                /** CAMERA  */
                val imageFile = File(recoverImagePath(context))
                Uri.fromFile(imageFile)
            } else {
                /** ALBUM  */
                intent?.data
            }
            Timber.d("selectedImage: %s", selectedImage?.toString() ?: "empty image")
            return selectedImage
        }
        return null
    }

    private fun isCamera(intent: Intent?): Boolean {
        return when {
            intent == null -> true
            intent.data == null -> true
            else -> false
        }
    }

    private fun getRotation(context: Context, imageUri: Uri, isCamera: Boolean): Int {
        val rotation: Int = if (isCamera) {
            getRotationFromCamera(context, imageUri)
        } else {
            getRotationFromGallery(context, imageUri)
        }
        Timber.d("Image rotation: %d", rotation)
        return rotation
    }

    private fun getRotationFromCamera(context: Context, imageFile: Uri): Int {
        var rotate = 0
        try {
            context.contentResolver.notifyChange(imageFile, null)
            val exif = ExifInterface(imageFile.path)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
            }
        } catch (e: Exception) {
            Timber.e(e)
        }

        return rotate
    }

    private fun getRotationFromGallery(context: Context, imageUri: Uri): Int {
        val columns = arrayOf(MediaStore.Images.Media.ORIENTATION)
        val cursor = context.contentResolver.query(imageUri, columns, null, null, null) ?: return 0

        cursor.moveToFirst()

        val orientationColumnIndex = cursor.getColumnIndex(columns[0])
        return cursor.getInt(orientationColumnIndex)
    }

    private fun addIntentToList(context: Context, list: MutableList<Intent>, intent: Intent) {
        val resInfo = context.packageManager.queryIntentActivities(intent, 0)
        for (resolveInfo in resInfo) {
            val packageName = resolveInfo.activityInfo.packageName
            val targetedIntent = Intent(intent)
            targetedIntent.`package` = packageName
            list.add(targetedIntent)
            Timber.d("Intent: %s package: %s", intent.action, packageName)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )
    }

    private fun saveImagePath(context: Context, path: String) {
        getDefaultSharedPreferences(context).edit().putString(TEMP_IMAGE_NAME, path).apply()
    }

    private fun recoverImagePath(context: Context): String {
        val sharedPreferences = getDefaultSharedPreferences(context)
        val path = sharedPreferences.getString(TEMP_IMAGE_NAME, "")
        sharedPreferences.edit().putString(TEMP_IMAGE_NAME, "").apply()
        return path
    }

}
