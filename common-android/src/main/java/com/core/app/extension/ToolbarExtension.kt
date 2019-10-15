package com.core.app.extension

import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.core.app.util.StatusBarUtils

fun Toolbar.alignToStatusBarBottom() {
    layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.also {
        it.setMargins(0, StatusBarUtils.getHeight(context), 0, 0)
    }
}