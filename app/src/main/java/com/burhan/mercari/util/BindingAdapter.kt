package com.burhan.mercari.util

/**
 * Developed by tcbaras on 2019-06-08.
 */

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Binding adapter used to hide the spinner once data is available
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("visibleIfSoldOut")
fun visibleIfSoldOut(view: View, it: Boolean?) {
    view.visibility = if (it != null && it) View.VISIBLE else View.GONE
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context).load(url).into(imageView)
}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: Int) {
    Glide.with(imageView.context).load(url).into(imageView)
}

