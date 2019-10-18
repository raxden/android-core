package com.core.data.local.converter

import androidx.room.TypeConverter
import com.core.commons.extension.toLocalDateTime
import com.core.commons.extension.toMilliseconds
import java.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun toLocalDateTime(time: Long?): LocalDateTime? = time?.toLocalDateTime()

    @TypeConverter
    fun toLong(time: LocalDateTime?): Long? = time?.toMilliseconds()
}