package com.github.albertopeam.spoktify.app.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.albertopeam.spoktify.ui.items.SectionsRecyclerViewAdapter
import com.github.albertopeam.spoktify.ui.items.model.Section
import com.hannesdorfmann.adapterdelegates4.AbsDelegationAdapter

@BindingAdapter("sections")
fun RecyclerView.sections(sections: List<Section>?) {
    val adapter = this.adapter
    if (adapter is SectionsRecyclerViewAdapter && sections != null) {
        adapter.sections = sections
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("items")
fun RecyclerView.items(items: List<*>?) {
    val adapter = this.adapter
    if (adapter is AbsDelegationAdapter<*> && items != null) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }
}