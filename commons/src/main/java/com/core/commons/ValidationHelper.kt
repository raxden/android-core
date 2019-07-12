package com.core.commons

import android.util.Patterns
import java.util.regex.Pattern

object ValidationHelper {

    private val PASSWORD_PATTERN = Pattern.compile("^[0-8A-Za-z!@#$%]{8,}$")

    fun isEmail(email: String): Boolean {
        return if (email.isEmpty()) false else Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPassword(password: String): Boolean {
        return if (password.isEmpty()) false else PASSWORD_PATTERN.matcher(password).matches()
    }

    fun isPhone(phone: String): Boolean {
        return if (phone.isEmpty()) false else Patterns.PHONE.matcher(phone).matches()
    }

    fun isURL(url: String): Boolean {
        return if (url.isEmpty()) false else Patterns.WEB_URL.matcher(url).matches()
    }
}
