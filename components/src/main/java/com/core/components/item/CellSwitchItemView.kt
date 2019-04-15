package com.core.components.item

import android.content.Context
import android.os.Build
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import com.core.components.BaseComponent2View
import com.core.components.R
import kotlinx.android.synthetic.main.cell_switch_item_view.view.*

class CellSwitchItemView : BaseComponent2View {

    private var titleTextAppearance: Int = 0
    private var subtitleTextAppearance: Int = 0

    private var mTitle: String? = ""
    private var mSubtitle: String? = ""
    private var mSwitchChecked: Boolean = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun getLayoutId() = R.layout.cell_switch_item_view

    private fun loadView(context: Context, layoutId: Int) {
        View.inflate(context, layoutId, this)
    }

    override fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CellSwitchItemView, 0, 0)
        mTitle = typedArray.getString(R.styleable.CellSwitchItemView_title)
        mSubtitle = typedArray.getString(R.styleable.CellSwitchItemView_subtitle)
        titleTextAppearance = typedArray.getResourceId(R.styleable.CellSwitchItemView_titleTextAppearance, 0)
        subtitleTextAppearance = typedArray.getResourceId(R.styleable.CellSwitchItemView_subtitleTextAppearance, 0)
        mSwitchChecked = typedArray.getBoolean(R.styleable.CellSwitchItemView_switch_checked, false)
        typedArray.recycle()
    }

    override fun bindViews() {
        if (titleTextAppearance != 0) setTextAppearance(title_text_view, titleTextAppearance)
        if (subtitleTextAppearance != 0) setTextAppearance(subtitle_text_view, subtitleTextAppearance)
    }

    override fun loadData() {
        setTitle(mTitle)
        setSubtitle(mSubtitle)
        setSwitchChecked(mSwitchChecked)
    }

    fun setTitle(title: String?) {
        title_text_view.text = title
        if (!title.isNullOrEmpty()) {
            title_text_view.visibility = View.VISIBLE
        } else {
            title_text_view.visibility = View.GONE
        }
    }

    fun setSubtitle(subtitle: String?) {
        subtitle_text_view.text = subtitle
        if (!subtitle.isNullOrEmpty()) {
            subtitle_text_view.visibility = View.VISIBLE
        } else {
            subtitle_text_view.visibility = View.GONE
        }
    }

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        switch_view.setOnCheckedChangeListener(listener)
    }

    fun setSwitchEnabled(enabled: Boolean) {
        switch_view.isEnabled = enabled
    }

    fun setSwitchChecked(checked: Boolean) {
        switch_view.isChecked = checked
    }

    private fun setTextAppearance(textView: AppCompatTextView, textAppearance: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) textView.setTextAppearance(textAppearance)
        else textView.setTextAppearance(context, textAppearance)
    }

}
