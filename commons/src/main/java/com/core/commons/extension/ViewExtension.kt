package com.core.commons.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat

fun View.isVisible() = visibility == View.VISIBLE

fun View.visible() { visibility = View.VISIBLE }

fun View.gone() { visibility = View.GONE }

fun View.isGone() = visibility == View.GONE

fun View.invisible() { visibility = View.INVISIBLE }

fun View.isInvisible() = visibility == View.INVISIBLE

fun View.getColor(resId: Int) = ContextCompat.getColor(context, resId)

fun View.getDrawable(resId: Int) = ContextCompat.getDrawable(context, resId)

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View = LayoutInflater.from(context)
        .inflate(layoutRes, this, false)

fun View.startFadeInAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
}

fun View.startFadeOutAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out))
}
