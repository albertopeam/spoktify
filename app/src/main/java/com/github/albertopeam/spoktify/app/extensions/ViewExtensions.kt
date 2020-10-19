package com.github.albertopeam.spoktify.app.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visibility")
fun View.visibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}