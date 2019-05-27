package com.core.commons.extension

import android.util.Xml
import timber.log.Timber
import java.io.*

fun InputStream.closeSilently() {
    try {
        close()
    } catch (e: IOException) {
        Timber.e(e)
    }
}

fun ObjectInputStream.closeSilently() {
    try {
        close()
    } catch (e: IOException) {
        Timber.e(e)
    }
}

fun InputStream.readContent(encoding: Xml.Encoding = Xml.Encoding.UTF_8): String {
    val sb = StringBuilder()
    try {
        val reader = BufferedReader(InputStreamReader(this, encoding.toString()))
        for (line in reader.readLine()) sb.append(line).append("\n")
    } catch (e: UnsupportedEncodingException) {
        Timber.e(e)
    } catch (e: IOException) {
        Timber.e(e)
    } finally {
        closeSilently()
    }
    return sb.toString()
}
