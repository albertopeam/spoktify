package com.github.albertopeam.domain

import java.util.*

data class Album(val id: String,
                 val name: String,
                 val releaseDate: Date,
                 val numTracks: Int,
                 val image: String,
                 val tracks: List<Track>) {}