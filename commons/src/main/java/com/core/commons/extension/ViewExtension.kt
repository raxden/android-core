package com.core.commons.extension

import android.view.View
import androidx.core.content.ContextCompat

fun View.getColor(resId: Int) = ContextCompat.getColor(context, resId)

fun View.getDrawable(resId: Int) = ContextCompat.getDrawable(context, resId)
