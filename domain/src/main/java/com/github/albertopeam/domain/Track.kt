package com.github.albertopeam.domain

import java.util.*

data class Track(val id: String,
                 val title: String,
                 val popularity: Int,
                 val url: String,
                 val explicit: Boolean,
                 val album: Album) {}