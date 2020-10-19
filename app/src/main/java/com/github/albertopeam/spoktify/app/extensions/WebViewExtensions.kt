package com.github.albertopeam.spoktify.app.extensions

import android.webkit.WebView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadUrl")
fun WebView.loadUrl(url: String) {
    loadUrl(url)
}