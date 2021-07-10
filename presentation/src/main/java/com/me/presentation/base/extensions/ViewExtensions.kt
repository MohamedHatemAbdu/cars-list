package com.me.presentation.base.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide


fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

fun ImageView.loadImage(url: String, placeHolderDrwble: Drawable) =
    Glide.with(this).load(url).centerCrop()
        .placeholder(placeHolderDrwble).into(this)


