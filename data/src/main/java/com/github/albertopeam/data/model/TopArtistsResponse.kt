package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class TopArtistsResponse(@SerializedName("items") val items: List<ArtistItemResponse>) {
}