package com.github.albertopeam.spoktify.ui.items.model

import com.github.albertopeam.domain.models.Category

data class CategoryItemViewModel(private val category: Category) {
    val name: String = category.name
    val imageUrl: String = category.image
}