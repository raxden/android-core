package com.core.data.local.converter

import android.net.Uri
import androidx.room.TypeConverter

class UriConverter {

    @TypeConverter
    fun toUri(uri: String?): Uri? = uri?.let { Uri.parse(it) }

    @TypeConverter
    fun toString(uri: Uri?): String? = uri?.let { uri.toString() }
}