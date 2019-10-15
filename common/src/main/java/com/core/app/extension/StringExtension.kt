package com.core.app.extension

import android.util.Base64
import timber.log.Timber
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.encodeToBase64(charset: Charset = Charsets.UTF_8, flags: Int = Base64.NO_WRAP): String {
    var result: ByteArray? = null
    try {
        result = toByteArray(charset)
    } catch (e: UnsupportedEncodingException) {
        Timber.e(e)
    }
    return Base64.encodeToString(result, flags)
}

fun String.decodeFromBase64(charset: Charset = Charsets.UTF_8, flags: Int = Base64.NO_WRAP): String {
    var decodedString = ""
    try {
        decodedString = String(Base64.decode(this, flags), charset)
    } catch (e: UnsupportedEncodingException) {
        Timber.e(e)
    }
    return decodedString
}

fun String.toSHA256(charset: Charset = Charsets.UTF_8): String {
    return hash(this, "SHA-256", charset)
}

fun String.toSHA512(charset: Charset = Charsets.UTF_8): String {
    return hash(this, "SHA-512", charset)
}

private fun hash(key: String, algorithm: String, charset: Charset): String {
    return digest(key, algorithm, charset)?.let { bytesToHexString(it) } ?: ""
}

private fun digest(key: String, algorithm: String, charset: Charset = Charsets.UTF_8): ByteArray? {
    var digest: ByteArray? = null
    try {
        val messageDigest = MessageDigest.getInstance(algorithm)
        messageDigest.update(key.toByteArray(charset))
        digest = messageDigest.digest()
    } catch (e: NoSuchAlgorithmException) {
        Timber.e(e)
    } catch (e: UnsupportedEncodingException) {
        Timber.e(e)
    }
    return digest
}

private fun bytesToHexString(bytes: ByteArray): String {
    // http://stackoverflow.com/questions/332079
    val sb = StringBuilder()
    for (i in bytes.indices) {
        val hex = Integer.toHexString(0xFF and bytes[i].toInt())
        if (hex.length == 1) {
            sb.append('0')
        }
        sb.append(hex)
    }
    return sb.toString()
}