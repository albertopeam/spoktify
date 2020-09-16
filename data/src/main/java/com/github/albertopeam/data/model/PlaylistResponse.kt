package com.github.albertopeam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class PlaylistResponse(@SerializedName("href")  val href: String? = null,
                                     @SerializedName("items") val items: List<PlaylistItemResponse>,
                                     @SerializedName("limit") val limit: Int,
                                     @SerializedName("next") val next: Any,
                                     @SerializedName("offset") val offset: Int,
                                     @SerializedName("previous") val previous: Any,
                                     @SerializedName("total") val total: Int) {
}