package com.github.albertopeam.spoktify.ui.items

import com.github.albertopeam.domain.Playlist

data class PlaylistItemViewModel(private val playlist: Playlist) {
    val title: String = playlist.name
    val imageUrl: String = playlist.image
}