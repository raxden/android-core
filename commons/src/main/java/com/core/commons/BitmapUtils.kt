package com.core.commons

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException

object BitmapUtils {

    fun decode(context: Context, uri: Uri): Bitmap? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            return BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            Timber.e(e)
        }
        return null
    }
}