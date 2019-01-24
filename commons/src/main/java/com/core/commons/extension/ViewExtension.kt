package com.core.commons.extension

import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat

fun View.getColor(resId: Int) = ContextCompat.getColor(context, resId)

fun View.getDrawable(resId: Int) = ContextCompat.getDrawable(context, resId)

fun View.startFadeInAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_in))
}

fun View.startFadeOutAnimation() {
    startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.fade_out))
}
