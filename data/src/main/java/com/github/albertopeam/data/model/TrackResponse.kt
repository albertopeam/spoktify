package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class TrackResponse(@SerializedName("album") val album: AlbumResponse,
                         @SerializedName("artists") val artists: List<ArtistItemResponse>,
                         @SerializedName("id") val id: String,
                         @SerializedName("explicit") val explicit: Boolean,
                         @SerializedName("name") val name: String,
                         @SerializedName("popularity") val popularity: Int,
                         @SerializedName("preview_url") val previewUrl: String?)