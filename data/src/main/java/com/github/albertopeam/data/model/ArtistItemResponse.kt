package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class ArtistItemResponse(@SerializedName("id") val id: String,
                              @SerializedName("name") val name: String,
                              @SerializedName("images") val images: List<ImageResponse>? = null) {
    fun imageUrl(): String? {
        return images?.first()?.url
    }
}