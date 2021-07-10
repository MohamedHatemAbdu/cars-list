package com.me.presentation.base.adapter

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.me.presentation.base.extensions.loadImage


object BindingAdapters {


    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "fallbackImg"], requireAll = true)
    fun ImageView.imageUrl(url: String, drawable: Drawable) {
        loadImage(url, drawable)
    }
}