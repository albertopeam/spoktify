package com.github.albertopeam.domain.models

import com.github.albertopeam.domain.models.Album

data class Track(val id: String,
                 val title: String,
                 val popularity: Int,
                 val url: String,
                 val explicit: Boolean,
                 val album: Album
) {}