package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

 data class PlaylistItemResponse(@SerializedName("description") val description: String,
                                         @SerializedName("id") val id: String,
                                         @SerializedName("images") val images: List<ImageResponse>? = null,
                                         @SerializedName("name") val name: String) {
    fun imageUrl(): String? {
        return images?.first()?.url
    }
}