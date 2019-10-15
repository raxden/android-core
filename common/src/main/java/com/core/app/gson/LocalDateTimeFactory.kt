package com.core.commons.gson

import com.core.commons.extension.toLocalDateTime
import com.core.commons.extension.toMilliseconds
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDateTime

class LocalDateTimeFactory : TypeAdapter<LocalDateTime>() {
    override fun write(writer: JsonWriter?, localDateTime: LocalDateTime?) {
        writer?.run {
            value(localDateTime?.toMilliseconds())
        }
    }

    override fun read(reader: JsonReader?): LocalDateTime? {
        reader?.run {
            val date = reader.nextLong()
            return date.toLocalDateTime()
        }
        return null
    }
}