package com.core.components.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.NumberPicker
import org.threeten.bp.Year

class YearPicker : NumberPicker {

    interface OnYearPickerListener {
        fun onYearChange(picker: NumberPicker, oldVal: Year, newVal: Year)
    }

    private val mYearList = mutableListOf<String>().apply {
        for (i in Year.now().value - 100.. Year.now().value) {
            add(i.toString())
        }
    }

    private var mOnYearPickerListener: OnYearPickerListener? = null

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

    fun setYear(year: Year) {
        value = year.value
    }

    fun setOnYearPickerListener(listener: OnYearPickerListener) {
        mOnYearPickerListener = listener
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (!isInEditMode) {
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            minValue = Year.now().value - 100
            maxValue = Year.now().value
            value = mYearList.size
            displayedValues = mYearList.toTypedArray()
            setOnValueChangedListener { picker, oldVal, newVal ->
                mOnYearPickerListener?.onYearChange(picker, Year.of(oldVal), Year.of(newVal))
            }
        }
    }

}