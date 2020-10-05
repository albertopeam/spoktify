package com.github.albertopeam.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TracksResponse {
    @SerializedName("href")
    @Expose
    var href: String? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
}