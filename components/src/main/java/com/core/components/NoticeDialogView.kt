package com.core.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import kotlinx.android.synthetic.main.notice_dialog_view.view.*

class NoticeDialogView : BaseComponent2View {

    private var titleTextAppearance: Int = 0
    private var descriptionTextAppearance: Int = 0
    private var acceptTextAppearance: Int = 0
    private var denyTextAppearance: Int = 0
    private var optionTextAppearance: Int = 0
    private var acceptGravityText: Int = 0
    private var denyGravityText: Int = 0
    private var optionGravityText: Int = 0
    private var acceptBackgroundDrawable: Drawable? = null
    private var denyBackgroundDrawable: Drawable? = null
    private var optionBackgroundDrawable: Drawable? = null

    private var acceptLabel: String = ""
    private var denyLabel: String = ""

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun loadAttributes(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.NoticeDialogView, 0, 0)
        acceptLabel = typedArray.getString(R.styleable.NoticeDialogView_acceptText) ?: ""
        denyLabel = typedArray.getString(R.styleable.NoticeDialogView_denyText) ?: ""
        titleTextAppearance = typedArray.getResourceId(R.styleable.NoticeDialogView_titleTextAppearance, 0)
        descriptionTextAppearance = typedArray.getResourceId(R.styleable.NoticeDialogView_descriptionTextAppearance, 0)
        acceptTextAppearance = typedArray.getResourceId(R.styleable.NoticeDialogView_acceptTextAppearance, 0)
        denyTextAppearance = typedArray.getResourceId(R.styleable.NoticeDialogView_denyTextAppearance, 0)
        optionTextAppearance = typedArray.getResourceId(R.styleable.NoticeDialogView_optionTextAppearance, 0)
        acceptGravityText = typedArray.getInt(R.styleable.NoticeDialogView_acceptGravityText, Gravity.CENTER_VERTICAL or Gravity.END)
        denyGravityText = typedArray.getInt(R.styleable.NoticeDialogView_denyGravityText, Gravity.CENTER_VERTICAL or Gravity.START)
        optionGravityText = typedArray.getInt(R.styleable.NoticeDialogView_optionGravityText, Gravity.CENTER_VERTICAL or Gravity.CENTER)
        acceptBackgroundDrawable = typedArray.getDrawable(R.styleable.NoticeDialogView_acceptBackgroundDrawable)
        denyBackgroundDrawable = typedArray.getDrawable(R.styleable.NoticeDialogView_denyBackgroundDrawable)
        optionBackgroundDrawable = typedArray.getDrawable(R.styleable.NoticeDialogView_optionBackgroundDrawable)
    }

    override fun bindViews() {
        if (titleTextAppearance != 0) setTextAppearance(title_text_view, titleTextAppearance)
        if (descriptionTextAppearance != 0) setTextAppearance(description_text_view, descriptionTextAppearance)
        if (acceptTextAppearance != 0) setTextAppearance(accept_text_view, acceptTextAppearance)
        if (denyTextAppearance != 0) setTextAppearance(deny_text_view, denyTextAppearance)
        if (acceptGravityText != 0) setAcceptGravityText(acceptGravityText)
        if (denyGravityText != 0) setDenyGravityText(denyGravityText)
        acceptBackgroundDrawable?.let { setAcceptBackgroundDrawable(it) }
        denyBackgroundDrawable?.let { setDenyBackgroundDrawable(it) }
    }

    override fun getLayoutId() = R.layout.notice_dialog_view

    override fun loadData() {
        setAcceptTextButton(acceptLabel)
        setDenyTextButton(denyLabel)
    }

    fun setTitleText(text: String) {
        title_text_view.text = text
        title_text_view.visibility = View.VISIBLE.takeIf { text.isNotEmpty() } ?: View.GONE
    }

    fun setDescriptionText(text: String) {
        description_text_view.text = text
        description_text_view.visibility = View.VISIBLE.takeIf { text.isNotEmpty() } ?: View.GONE
    }

    fun setAcceptTextButton(text: String, onClickListener: View.OnClickListener? = null) {
        accept_text_view.text = text
        accept_text_view.setOnClickListener(onClickListener)
        accept_text_view.visibility = View.VISIBLE.takeIf { text.isNotEmpty() } ?: View.INVISIBLE
    }

    fun setDenyTextButton(text: String, onClickListener: View.OnClickListener? = null) {
        deny_text_view.text = text
        deny_text_view.setOnClickListener(onClickListener)
        deny_text_view.visibility = View.VISIBLE.takeIf { text.isNotEmpty() } ?: View.INVISIBLE
    }

    fun setAcceptGravityText(gravity: Int) {
        accept_text_view.gravity = gravity
    }

    fun setDenyGravityText(gravity: Int) {
        deny_text_view.gravity = gravity
    }

    fun setAcceptBackgroundDrawable(drawable: Drawable) {
        accept_text_view.background = drawable
    }

    fun setDenyBackgroundDrawable(drawable: Drawable) {
        deny_text_view.background = drawable
    }

    fun setAcceptVisibility(visibility: Int) {
        accept_text_view.visibility = visibility
    }

    fun setDenyVisibility(visibility: Int) {
        deny_text_view.visibility = visibility
    }

    fun addOptionView(id: Int, label: String, onclickListener: View.OnClickListener) {
        val option = AppCompatTextView(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt())
            this.id = id
            setOnClickListener(onclickListener)
            text = label
            gravity = optionGravityText
            background = optionBackgroundDrawable
        }
        setTextAppearance(option, optionTextAppearance)
        options_container.addView(option)
    }

    private fun setTextAppearance(textView: AppCompatTextView, textAppearance: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) textView.setTextAppearance(textAppearance)
        else textView.setTextAppearance(context, textAppearance)
    }

}
