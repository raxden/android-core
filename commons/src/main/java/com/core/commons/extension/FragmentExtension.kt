package com.core.commons.extension

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.getDrawable(resourceId: Int) = context?.let { ContextCompat.getDrawable(it, resourceId) }

fun Fragment.getColor(resourceId: Int) = context?.let { ContextCompat.getColor(it, resourceId) }

fun Fragment.getLayoutId(): Int = javaClass.simpleName
        .decapitalize()
        .split("(?=\\p{Upper})".toRegex())
        .joinToString(separator = "_")
        .toLowerCase()
        .takeIf { it.isNotEmpty() }?.let {
            resources.getIdentifier(it.replace("R.layout.", ""), "layout", context?.packageName)
        } ?: 0
