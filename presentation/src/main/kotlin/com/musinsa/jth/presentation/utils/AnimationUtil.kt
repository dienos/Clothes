package com.musinsa.jth.presentation.utils

import android.content.Context
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat

class AnimationUtil(context: Context, drawableId: Int) {
    private val vector: AnimatedVectorDrawable =
        ContextCompat.getDrawable(context, drawableId) as AnimatedVectorDrawable

    fun playAnimation(imageView: ImageView, onFinish: () -> Unit = {}) {
        imageView.setImageDrawable(vector)
        imageView.visibility = View.VISIBLE
        vector.start()
        vector.registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                super.onAnimationEnd(drawable)
                imageView.visibility = View.INVISIBLE
                vector.stop()
                onFinish()
            }
        })
    }
}