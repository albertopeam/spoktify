package com.github.albertopeam.spoktify.app.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.albertopeam.spoktify.ui.items.PlaylistItemViewModel
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

@BindingAdapter("data")
fun RecyclerView.data(data: List<*>?) {
    val adapter = this?.adapter
    if (adapter is AbsDelegationAdapter<*> && data != null) {
        adapter.items = data
        adapter.notifyDataSetChanged()
    }
}