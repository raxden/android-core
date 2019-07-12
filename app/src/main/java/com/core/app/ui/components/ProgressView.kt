package com.core.app.ui.components

import android.content.Context
import android.content.res.TypedArray

import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.core.app.R
import com.core.app.base.component.BaseComponentView
import com.core.commons.extension.*

class ProgressView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = 0
) : BaseComponentView(context, attrs, defStyleAttr, defStyleRes) {

    private var mContainer: View? = null
    private var mProgressBar: ProgressBar? = null
    private var mProgressTextView: AppCompatTextView? = null

    private var mTextColor: Int = 0
    private var mBackgroundColor: Int = 0
    private var mIndeterminateTint: Int = 0

    private var mProgressText: String? = null

    override val mStyleable: IntArray
        get() = R.styleable.ProgressView

    override val mLayoutId: Int
        get() = R.layout.progress_view

    override fun onLoadStyledAttributes(attrs: TypedArray) {
        mBackgroundColor = attrs.getColor(R.styleable.ProgressView_progressBackgroundColor, -1)
        mTextColor = attrs.getColor(R.styleable.ProgressView_progressTextColor, -1)
        mProgressText = attrs.getString(R.styleable.ProgressView_progressText)
        mIndeterminateTint = attrs.getColor(R.styleable.ProgressView_progressIndeterminateTint, -1)
    }

    override fun onViewCreated() {
        mContainer = findViewById(R.id.progress)
        mProgressBar = findViewById(R.id.progressbar)
        mProgressTextView = findViewById(R.id.progress_label)

        setProgressText(mProgressText)
        setProgressTextColor(mTextColor)
        setProgressBackgroundColor(mBackgroundColor)
        setProgressIndeterminateTint(mIndeterminateTint)
    }

    override fun setVisibility(visibility: Int) {
        when (visibility) {
            View.VISIBLE -> startFadeInAnimation()
            View.INVISIBLE -> startFadeOutAnimation()
            View.GONE -> startFadeOutAnimation()
        }
        super.setVisibility(visibility)
    }

    fun setProgressText(resourceId: Int?) {
        if (mProgressTextView == null || resourceId == null || resourceId == 0) return
        mProgressTextView?.text = context.resources.getString(resourceId)
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
