package com.github.albertopeam.spoktify.app.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.albertopeam.spoktify.R

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(imageUrl: String?){
    imageUrl?.let {
        Glide.with(context)
            .load(it)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_disc_player)
                    .error(R.drawable.ic_error_red))
            .into(this)
    }
}

@BindingAdapter("circleImageUrl")
fun ImageView.circleImageUrl(imageUrl: String?){
    imageUrl?.let {
        Glide.with(context)
            .load(it)
            .circleCrop()
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_disc_player)
                    .error(R.drawable.ic_error_red))
            .into(this)
    }
}
