package com.github.albertopeam.data.model

import com.google.gson.annotations.SerializedName

data class TracksResponse(@SerializedName("tracks") val tracks: TrackResponse)

