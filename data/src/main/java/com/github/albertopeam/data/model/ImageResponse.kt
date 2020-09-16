package com.github.albertopeam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal class ImageResponse {
    @SerializedName("url")
    @Expose
    var url: String? = null
}