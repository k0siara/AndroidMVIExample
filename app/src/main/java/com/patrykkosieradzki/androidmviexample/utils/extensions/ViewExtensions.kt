package com.patrykkosieradzki.androidmviexample.utils.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

private const val FULL_OPACITY = 1f

fun View.goneIfWithAnimation(shouldBeGone: Boolean) {
    val mediumAnimationDuration = resources.getInteger(android.R.integer.config_mediumAnimTime)
    if (shouldBeGone) {
        if (visibility == GONE) {
            return
        }
        animate()
            .alpha(0f)
            .setDuration(mediumAnimationDuration.toLong())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = GONE
                }
            })
    } else {
        if (visibility == VISIBLE) {
            return
        }
        alpha = 0f
        visibility = VISIBLE

        animate()
            .alpha(FULL_OPACITY)
            .setDuration(mediumAnimationDuration.toLong())
            .setListener(null)
    }
}
