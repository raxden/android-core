package com.core.commons.extension

import com.core.commons.gson.LocalDateFactory
import com.core.commons.gson.LocalDateTimeFactory
import com.google.gson.*
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

inline fun <reified T : Any> Any.mapTo(): T = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateFactory())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeFactory())
        .create()
        .run { fromJson(toJson(this@mapTo), T::class.java) }

inline fun <reified T : Any> Any.mapTo(gson: Gson): T = gson
        .run { fromJson(toJson(this@mapTo), T::class.java) }
