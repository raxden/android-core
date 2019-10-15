package com.core.commons.gson

import com.core.commons.extension.toLocalDate
import com.core.commons.extension.toMilliseconds
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