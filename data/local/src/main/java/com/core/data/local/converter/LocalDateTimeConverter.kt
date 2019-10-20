package com.core.data.local.converter

import androidx.room.TypeConverter
import com.core.common.android.extensions.toLocalDateTime
import com.core.common.android.extensions.toMilliseconds
import org.threeten.bp.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun toLocalDateTime(time: Long?): LocalDateTime? = time?.toLocalDateTime()

    @TypeConverter
    fun toLong(time: LocalDateTime?): Long? = time?.toMilliseconds()
}