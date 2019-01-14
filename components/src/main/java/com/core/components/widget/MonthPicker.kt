package com.core.components.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import org.threeten.bp.Month
import org.threeten.bp.format.TextStyle
import java.util.*

class MonthPicker : NumberPicker {

    interface OnMonthPickerListener {
        fun onMonthChange(picker: NumberPicker, oldVal: Month, newVal: Month)
    }

    private val mMonthList = mutableListOf<String>().apply {
        add(Month.JANUARY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.FEBRUARY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.MARCH.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.APRIL.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.MAY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.JANUARY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.JULY.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.AUGUST.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.SEPTEMBER.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.OCTOBER.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.NOVEMBER.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        add(Month.DECEMBER.getDisplayName(TextStyle.FULL, Locale.getDefault()))
    }

    private var mOnMonthPickerListener: OnMonthPickerListener? = null

    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    fun setMonth(month: Month) {
        value = month.value
    }

    fun setOnMonthPickerListener(listener: OnMonthPickerListener) {
        mOnMonthPickerListener = listener
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (!isInEditMode) {
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            minValue = 1
            maxValue = mMonthList.size
            displayedValues = mMonthList.toTypedArray()
            setOnValueChangedListener { picker, oldVal, newVal ->
                mOnMonthPickerListener?.onMonthChange(picker, Month.of(oldVal), Month.of(newVal))
            }
        }
    }

}