package com.github.albertopeam.spoktify.ui.items.model

import com.github.albertopeam.domain.models.Playlist

data class PlaylistItemViewModel(private val playlist: Playlist) {
    val title: String = playlist.name
    val imageUrl: String = playlist.image
}