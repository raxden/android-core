package com.core.commons

import android.app.Activity
import android.content.Context
import android.view.View

import com.core.commons.SDKUtils

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
