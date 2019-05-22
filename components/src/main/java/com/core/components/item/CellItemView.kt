package com.core.components.item

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import com.core.commons.extension.setCompatTextAppearance
import com.core.components.BaseComponentView
import com.core.components.R
import kotlinx.android.synthetic.main.cell_item_view.view.*

class CellItemView : BaseComponentView {

    private var titleTextAppearance: Int = 0
    private var subtitleTextAppearance: Int = 0

    private var mTitle: String? = ""
    private var mSubtitle: String? = ""
    private var mImageLeftDrawable: Drawable? = null
    private var mImageLeftColor: Int? = null
    private var mImageRightDrawable: Drawable? = null
    private var mImageRightColor: Int? = null
    private var mSeparatorColor: Int? = null

    private var mTitleGravity: Int = 0
    private var mSubtitleGravity: Int = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun getLayoutId() = R.layout.cell_item_view

    override fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CellItemView, defStyleAttr, defStyleRes)
        mTitle = typedArray.getString(R.styleable.CellItemView_title)
        mSubtitle = typedArray.getString(R.styleable.CellItemView_subtitle)
        titleTextAppearance = typedArray.getResourceId(R.styleable.CellItemView_titleTextAppearance, 0)
        subtitleTextAppearance = typedArray.getResourceId(R.styleable.CellItemView_subtitleTextAppearance, 0)
        mTitleGravity = typedArray.getInt(R.styleable.CellItemView_title_gravity, Gravity.START or Gravity.BOTTOM)
        mSubtitleGravity = typedArray.getInt(R.styleable.CellItemView_subtitle_gravity, Gravity.START or Gravity.TOP)
        mImageLeftDrawable = typedArray.getDrawable(R.styleable.CellItemView_image_left_src)
        mImageLeftColor = typedArray.getColor(R.styleable.CellItemView_image_left_tint, 0)
        mImageRightDrawable = typedArray.getDrawable(R.styleable.CellItemView_image_right_src)
        mImageRightColor = typedArray.getColor(R.styleable.CellItemView_image_right_tint, 0)
        mSeparatorColor = typedArray.getColor(R.styleable.CellItemView_separator_color, 0)
        typedArray.recycle()
    }

    override fun bindViews() {
        if (titleTextAppearance != 0) title_text_view.setCompatTextAppearance(titleTextAppearance)
        if (subtitleTextAppearance != 0) subtitle_text_view.setCompatTextAppearance(subtitleTextAppearance)
    }

    override fun loadData() {
        setTitle(mTitle, mTitleGravity)
        setSubtitle(mSubtitle, mSubtitleGravity)
        mImageLeftDrawable?.let {
            when (mImageLeftColor) {
                0 -> setImageLeftDrawable(it)
                else -> setImageLeftDrawable(it, color = mImageLeftColor)
            }
        }
        mImageRightDrawable?.let {
            when (mImageRightColor) {
                0 -> setImageRightDrawable(it)
                else -> setImageRightDrawable(it, color = mImageRightColor)
            }
        }
        when (mSeparatorColor) {
            0 -> return
            else -> setSeparatorColor(mSeparatorColor)
        }
    }

    fun setTitle(title: String?) {
        setTitle(title, mTitleGravity)
    }

    fun setSubtitle(subtitle: String?) {
        setSubtitle(subtitle, mSubtitleGravity)
    }

    fun setImageLeftResource(resource: Int, width: Int = 0, height: Int = 0, color: Int? = null) {
        image_left_view.visibility = View.VISIBLE
        if (width > 0) image_left_view.layoutParams.width = width
        if (height > 0) image_left_view.layoutParams.height = height
        image_left_view.setImageResource(resource)
        color?.let { image_left_view.setColorFilter(it) }
    }

    fun setImageLeftDrawable(drawable: Drawable, width: Int = 0, height: Int = 0, color: Int? = null) {
        image_left_view.visibility = View.VISIBLE
        if (width > 0) image_left_view.layoutParams.width = width
        if (height > 0) image_left_view.layoutParams.height = height
        image_left_view.setImageDrawable(drawable)
        color?.let { image_left_view.setColorFilter(it) }
    }

    fun setImageRightResource(resource: Int, width: Int = 0, height: Int = 0, color: Int? = null) {
        image_right_view.visibility = View.VISIBLE
        if (width > 0) image_right_view.layoutParams.width = width
        if (height > 0) image_right_view.layoutParams.height = height
        image_right_view.setImageResource(resource)
        color?.let { image_right_view.setColorFilter(it) }
    }

    fun setImageRightDrawable(drawable: Drawable, width: Int = 0, height: Int = 0, color: Int? = null) {
        image_right_view.visibility = View.VISIBLE
        if (width > 0) image_right_view.layoutParams.width = width
        if (height > 0) image_right_view.layoutParams.height = height
        image_right_view.setImageDrawable(drawable)
        color?.let { image_right_view.setColorFilter(it) }
    }

    fun setSeparatorColor(color: Int? = null) {
        color?.let { separator_view.setBackgroundColor(color) }
    }

    fun clearImageLeft() {
        image_left_view.setImageDrawable(null)
    }

    fun clearImageRight() {
        image_right_view.setImageDrawable(null)
    }

    fun getImageLeft(): AppCompatImageView = image_left_view

    fun getImageRight(): AppCompatImageView = image_right_view

    private fun setTitle(title: String?, gravity: Int) {
        title_text_view.text = title
        title_text_view.gravity = gravity
        title_text_view.visibility = View.VISIBLE.takeIf { !title.isNullOrEmpty() } ?: View.GONE
    }

    private fun setSubtitle(subtitle: String?, gravity: Int) {
        subtitle_text_view.text = subtitle
        subtitle_text_view.gravity = gravity
        subtitle_text_view.visibility = View.VISIBLE.takeIf { !subtitle.isNullOrEmpty() } ?: View.GONE
    }

}
