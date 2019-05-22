package com.core.commons

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.core.commons.glide.ImageUtil

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("urlImage")
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