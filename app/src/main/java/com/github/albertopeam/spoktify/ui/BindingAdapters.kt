package com.github.albertopeam.spoktify.ui

import android.view.View
import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String) {
    view.loadUrl(url)
}

@BindingAdapter("visibility")
fun visibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}