package com.core.app.helper

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.core.app.R

class AnimationHelper(private val context: Context) {

    fun slideCardFromBottom(view: View, listener: Animation.AnimationListener? = null) {
        val anim = AnimationUtils.loadAnimation(context, R.anim.slide_card_from_bottom).apply {
            listener?.let { setAnimationListener(it) }
        }
        view.startAnimation(anim)
    }

}