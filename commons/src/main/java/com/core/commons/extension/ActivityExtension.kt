package com.core.commons.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.getExtras(): Bundle? = intent?.extras

fun Activity.finishOK() {
    setResult(Activity.RESULT_OK)
    finish()
}

fun Activity.finishOK(data: Intent) {
    setResult(Activity.RESULT_OK, data)
    finish()
}

fun Activity.getLayoutId(): Int = javaClass.simpleName
        .decapitalize()
        .split("(?=\\p{Upper})".toRegex())
        .joinToString(separator = "_")
        .toLowerCase()
        .takeIf { it.isNotEmpty() }?.let {
            resources.getIdentifier(it.replace("R.layout.", ""), "layout", packageName)
        } ?: 0
