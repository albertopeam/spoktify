package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class AlbumsResponse(@SerializedName("items") val items: List<AlbumResponse>)