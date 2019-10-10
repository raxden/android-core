package com.core.data.persistence.converter

import android.net.Uri
import androidx.room.TypeConverter
import com.core.commons.extension.toLocalDateTime
import com.core.commons.extension.toMilliseconds
import org.threeten.bp.LocalDateTime

class UriConverter {

    @TypeConverter
    fun toUri(uri: String?): Uri? = uri?.let { Uri.parse(it) }

    @TypeConverter
    fun toString(uri: Uri?): String? = uri?.let { uri.toString() }
}