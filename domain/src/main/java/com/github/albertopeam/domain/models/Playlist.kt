package com.github.albertopeam.domain.models

data class Playlist(val id: String,
                    val name: String,
                    val image: String,
                    val tracks: List<Track>) {}