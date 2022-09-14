package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class AlbumResponse(@SerializedName("id") val id: String,
                         @SerializedName("name") val name: String,
                         @SerializedName("release_date") val releaseDate: String,
                         @SerializedName("total_tracks") val totalTracks: Int,
                         @SerializedName("images") val images: List<ImageResponse>? = null,
                         @SerializedName("artists") val artists: List<ArtistItemResponse>? = null) {
    fun imageUrl(): String? {
        return images?.first()?.url
    }
}