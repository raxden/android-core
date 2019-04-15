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
