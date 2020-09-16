package com.github.albertopeam.domain

data class Playlist(val id: String,
                    val name: String,
                    val image: String,
                    val tracks: List<Track>) {}