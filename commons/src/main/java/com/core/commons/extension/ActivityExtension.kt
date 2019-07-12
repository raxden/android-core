package com.core.commons.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle

fun Activity.getExtras(): Bundle? = intent?.extras

fun Activity.finishOK(data: Intent? = null) {
    setResult(Activity.RESULT_OK, data)
    finish()
}