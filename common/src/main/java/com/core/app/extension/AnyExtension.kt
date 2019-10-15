package com.core.app.extension

import com.core.app.gson.LocalDateFactory
import com.core.app.gson.LocalDateTimeFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

inline fun <reified T : Any> Any.mapTo(gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateFactory())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeFactory())
        .create()
): T = gson.run { fromJson(toJson(this@mapTo)) }
