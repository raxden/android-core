package com.core.commons.extension

import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.raxdenstudios.commons.util.ToolbarUtils

fun Toolbar.alignToStatusBarBottom() {
    layoutParams = (layoutParams as? ViewGroup.MarginLayoutParams)?.also {
        it.setMargins(0, ToolbarUtils.getStatusBarHeight(context), 0, 0)
    }
}