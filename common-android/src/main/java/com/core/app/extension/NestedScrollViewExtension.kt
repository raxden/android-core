package com.core.app.extension

import androidx.core.widget.NestedScrollView

fun NestedScrollView.setOnPageEndlessListener(listener: OnPageEndlessListener) {
    setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (scrollY == (v.getChildAt(0).measuredHeight - v.measuredHeight))  {
            listener.onPageEndless()
        }
    })
}