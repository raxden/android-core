package com.core.app.extension

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Base64
import java.io.ByteArrayOutputStream
import kotlin.math.roundToInt

fun Bitmap.toBase64(maxSize: Int, quality: Int): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    resize(this, maxSize).compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
    return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
}

fun Bitmap.rotate(rotation: Int): Bitmap {
    if (rotation != 0) {
        val matrix = Matrix()
        matrix.postRotate(rotation.toFloat())
        return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
    }
    return this
}

fun Bitmap.resize(bitmap: Bitmap, maxSize: Int): Bitmap {
    var width = bitmap.width
    var height = bitmap.height
    val aspectRatio: Float
    if (bitmap.width > bitmap.height) {
        aspectRatio = (width / height).toFloat()
        if (bitmap.width > maxSize) width = maxSize
        height = (width / aspectRatio).roundToInt()
    } else {
        aspectRatio = (height / width).toFloat()
        if (bitmap.height > maxSize) height = maxSize
        width = (height / aspectRatio).roundToInt()
    }
    return Bitmap.createScaledBitmap(bitmap, width, height, true)
}
