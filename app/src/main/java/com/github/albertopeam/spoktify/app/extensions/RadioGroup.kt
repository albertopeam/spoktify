package com.github.albertopeam.spoktify.app.extensions

import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.databinding.BindingAdapter

@BindingAdapter("values")
fun RadioGroup.values(values: List<Boolean>) {
    if (childCount != values.size) { throw IllegalArgumentException() }
    children.map { it as RadioButton }
        .forEachIndexed { index, radioButton -> radioButton.isChecked = values[index] }
}