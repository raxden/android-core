package com.core.app.extension

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
        val reader = BufferedReader(InputStreamReader(this, encodingToString(encoding)))
        var line: String?
        do {
            line = reader.readLine()
            line?.also { sb.append(line).append("\n") }
        } while (line != null)
    } catch (e: UnsupportedEncodingException) {
        Timber.e(e)
    } catch (e: IOException) {
        Timber.e(e)
    } finally {
        closeSilently()
    }
    return sb.toString()
}

private fun encodingToString(encoding: Xml.Encoding): String {
    var enc = ""
    if (encoding == Xml.Encoding.UTF_8) {
        enc = "UTF-8"
    } else if (encoding == Xml.Encoding.ISO_8859_1) {
        enc = "ISO-8859_1"
    }
    return enc
}