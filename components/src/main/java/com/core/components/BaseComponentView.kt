package com.core.components

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

abstract class BaseComponentView : FrameLayout {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
            : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    fun init(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) {
        if (!isInEditMode) {
            loadView(context)
            loadAttributes(context, attrs, defStyleAttr, defStyleRes)
            bindViews()
            loadData()
        }
    }

    private fun loadView(context: Context) {
        val layoutId = getLayoutId()
        if (layoutId != 0)
            View.inflate(context, layoutId, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (isInEditMode) {
            loadView(context)
        }
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)

    protected abstract fun bindViews()

    protected abstract fun loadData()

}
