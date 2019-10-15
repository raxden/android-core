package com.core.app.gson

import com.core.app.extension.toLocalDate
import com.core.app.extension.toMilliseconds
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDate

class LocalDateFactory : TypeAdapter<LocalDate>() {
    override fun write(writer: JsonWriter?, localDate: LocalDate?) {
        writer?.run {
            value(localDate?.toMilliseconds())
        }
    }

    override fun read(reader: JsonReader?): LocalDate? {
        reader?.run {
            val date = reader.nextLong()
            return date.toLocalDate()
        }
        return null
    }
}