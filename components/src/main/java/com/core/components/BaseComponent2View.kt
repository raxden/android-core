package com.core.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

abstract class BaseComponent2View : FrameLayout {

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

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
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
