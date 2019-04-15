package com.core.commons.extension

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.getDrawable(resourceId: Int) = context?.let { ContextCompat.getDrawable(it, resourceId) }

fun Fragment.getColor(resourceId: Int) = context?.let { ContextCompat.getColor(it, resourceId) }