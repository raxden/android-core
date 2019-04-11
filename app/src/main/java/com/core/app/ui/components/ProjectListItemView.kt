package com.core.app.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.core.app.R

class ProjectListItemView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    init {
        context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.PieChart,
                0, 0).apply {

            try {
                mShowText = getBoolean(R.styleable.PieChart_showText, false)
                textPos = getInteger(R.styleable.PieChart_labelPosition, 0)
            } finally {
                recycle()
            }
        }
    }
}