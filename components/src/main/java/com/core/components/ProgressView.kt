package com.core.components

import android.content.Context

import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.core.commons.extension.*

class ProgressView : BaseComponent2View {

    private var mContainer: View? = null
    private var mProgressBar: ProgressBar? = null
    private var mProgressTextView: AppCompatTextView? = null

    private var mTextColor: Int = 0
    private var mBackgroundColor: Int = 0
    private var mIndeterminateTint: Int = 0

    private var mProgressText: String? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ProgressView, 0, 0).apply {
            try {
                mBackgroundColor = getColor(R.styleable.ProgressView_progressBackgroundColor, -1)
                mTextColor = getColor(R.styleable.ProgressView_progressTextColor, -1)
                mProgressText = getString(R.styleable.ProgressView_progressText)
                mIndeterminateTint = getColor(R.styleable.ProgressView_progressIndeterminateTint, -1)
            } finally {
                recycle()
            }
        }
    }

    override fun bindViews() {
        mContainer = findViewById(R.id.progress)
        mProgressBar = findViewById(R.id.progressbar)
        mProgressTextView = findViewById(R.id.progress_label)
    }

    override fun loadData() {
        setProgressText(mProgressText)
        setProgressTextColor(mTextColor)
        setProgressBackgroundColor(mBackgroundColor)
        setProgressIndeterminateTint(mIndeterminateTint)
    }

    override fun getLayoutId(): Int {
        return R.layout.progress_view
    }

    override fun setVisibility(visibility: Int) {
        when (visibility) {
            View.VISIBLE -> startFadeInAnimation()
            View.INVISIBLE -> startFadeOutAnimation()
            View.GONE -> startFadeOutAnimation()
        }
        super.setVisibility(visibility)
    }

    fun setProgressText(text: String?) {
        if (mProgressTextView == null || text == null) return
        mProgressTextView?.text = text
    }

    fun setProgressTextColor(textColor: Int) {
        if (mProgressTextView == null || textColor == -1) return
        mProgressTextView?.setTextColor(textColor)
    }

    fun setProgressBackgroundColor(backgroundColor: Int) {
        if (mContainer == null || backgroundColor == -1) return
        mContainer?.setBackgroundColor(backgroundColor)
    }

    fun setProgressIndeterminateTint(indeterminateTint: Int) {
        if (mProgressBar == null || indeterminateTint == -1) return
        val progressDrawable = mProgressBar?.indeterminateDrawable?.mutate()
        progressDrawable?.setColorFilter(indeterminateTint, android.graphics.PorterDuff.Mode.MULTIPLY)
        mProgressBar?.progressDrawable = progressDrawable
    }

}
