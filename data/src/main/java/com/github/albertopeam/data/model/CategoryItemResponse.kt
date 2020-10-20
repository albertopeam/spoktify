package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName


data class CategoryItemResponse(@SerializedName("id") val id: String,
                                @SerializedName("name") val name: String,
                                @SerializedName("icons") val icons: List<ImageResponse>) {
    fun imageUrl(): String? {
        return icons.first().url
    }
}