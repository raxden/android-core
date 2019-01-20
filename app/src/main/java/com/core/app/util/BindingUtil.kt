package com.core.app.util

import android.view.View
import androidx.databinding.BindingAdapter

object BindingUtil {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}