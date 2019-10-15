package com.core.app.gson

import com.core.app.extension.toLocalDateTime
import com.core.app.extension.toMilliseconds
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