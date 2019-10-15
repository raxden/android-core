package com.core.app.util

import android.app.Activity
import android.content.Context

/**
 * Created by Angel on 15/05/2017.
 */

object StatusBarUtils {

    fun setColor(activity: Activity, color: Int) {
        activity.window.statusBarColor = color
    }

    fun getHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}
