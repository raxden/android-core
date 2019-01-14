package com.core.commons.extension

import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

inline fun <reified T : Any> Any.mapTo(): T =
        GsonBuilder()
                .registerTypeAdapter(LocalDate::class.java, LocalDateFactory())
                .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeFactory())
                .create()
                .run { fromJson(toJson(this@mapTo), T::class.java) }

inline fun <reified T : Any> Any.mapTo(gson: Gson): T =
        gson.run { fromJson(toJson(this@mapTo), T::class.java) }

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