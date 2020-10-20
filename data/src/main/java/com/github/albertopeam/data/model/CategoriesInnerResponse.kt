package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesInnerResponse(@SerializedName("items") val items: List<CategoryItemResponse>)