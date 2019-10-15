package com.core.app.extension

import android.annotation.TargetApi
import android.net.Uri
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.util.Patterns
import android.view.View
import android.widget.TextView
import com.core.app.util.AppUtils

@Suppress("DEPRECATION")
@TargetApi(23)
fun TextView.setCompatTextAppearance(resId: Int) {
    if (AppUtils.hasMarshmallow()) setTextAppearance(resId)
    else setTextAppearance(context, resId)
}

fun TextView.setHtml(html: String, listener: OnHyperlinkClickListener? = null) {
    if (TextUtils.isEmpty(html)) text = ""
    else {
        var htmlContent = html
        val sequence = fromHtml(htmlContent)
        val strBuilder = SpannableStringBuilder(sequence)
        val urls = strBuilder.getSpans(0, sequence.length, URLSpan::class.java)
        if (urls != null && urls.isNotEmpty())
            for (span in urls)
                makeLinkClickable(strBuilder, span, listener)
        else {
            var hasLinks = false
            for (word in htmlContent.split(" ".toRegex())) {
                if (word.isNotEmpty() && Patterns.WEB_URL.matcher(word).matches()) {
                    hasLinks = true
                    htmlContent = htmlContent.replace(word, "<a href='$word'>$word</a>")
                }
            }
            if (hasLinks) {
                setHtml(htmlContent, listener)
                return
            }
        }
        text = strBuilder
        movementMethod = LinkMovementMethod.getInstance()
    }
}

@Suppress("DEPRECATION")
@TargetApi(24)
private fun fromHtml(html: String): Spanned {
    return if (AppUtils.hasNougat()) Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    else Html.fromHtml(html)
}

private fun makeLinkClickable(strBuilder: SpannableStringBuilder, span: URLSpan, listener: OnHyperlinkClickListener?) {
    val start = strBuilder.getSpanStart(span)
    val end = strBuilder.getSpanEnd(span)
    val flags = strBuilder.getSpanFlags(span)
    val clickable = object : ClickableSpan() {
        override fun onClick(view: View) {
            val uri = Uri.parse(span.url)
            listener?.onHyperlinkClick(uri)
        }
    }
    strBuilder.setSpan(clickable, start, end, flags)
    strBuilder.removeSpan(span)
}

interface OnHyperlinkClickListener {
    fun onHyperlinkClick(uri: Uri)
}