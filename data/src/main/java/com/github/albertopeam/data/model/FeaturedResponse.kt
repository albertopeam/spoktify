package com.github.albertopeam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class FeaturedResponse(@SerializedName("message")  val message: String,
                                     @SerializedName("playlists") val playlist: PlaylistResponse) {}