package com.core.app.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.core.commons.extension.setSafeOnClickListener
import com.core.commons.glide.ImageUtil
import com.google.android.material.textfield.TextInputLayout
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("bind:error")
    fun error(view: TextInputLayout, resourceId: Int) {
        if (resourceId != 0) view.error = view.context.getString(resourceId)
        else view.error = ""
    }

    @JvmStatic
    @BindingAdapter("bind:onSafeClick")
    fun click(view: View, listener: View.OnClickListener) {
        view.setSafeOnClickListener { listener.onClick(it) }
    }

    @JvmStatic
    @BindingAdapter("bind:selected")
    fun selected(view: View, selected: Boolean) {
        view.isSelected = selected
    }

    @JvmStatic
    @BindingAdapter("bind:visibleGone")
    fun visibleGone(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("bind:visibleInvisible")
    fun visibleInvisible(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:text_date", "bind:text_date_format"], requireAll = false)
    fun parseLocalDateTimeToDate(view: TextView, date: LocalDateTime?, format: String? = "dd MMM yyyy") {
        view.text = date?.format(DateTimeFormatter.ofPattern(format ?: "dd MMM yyyy")) ?: view.text
    }

    @JvmStatic
    @BindingAdapter(value = ["bind:text_date", "bind:text_date_format"], requireAll = false)
    fun parseLocalDateToDate(view: TextView, date: LocalDate?, format: String? = "dd MMM yyyy") {
        view.text = date?.format(DateTimeFormatter.ofPattern(format ?: "dd MMM yyyy")) ?: view.text
    }

    @JvmStatic
    @BindingAdapter("bind:urlImage")
    fun loadImage(view: ImageView, url: String?) {
        url?.run {
            if (view.getTag(view.id) == null || view.getTag(view.id) != (url)) {
                view.setImageBitmap(null)
                view.setTag(view.id, url)
                ImageUtil.loadImage(url, view)
            }
        } ?: view.run {
            setTag(id, null)
            setImageBitmap(null)
        }
    }
}