package com.github.albertopeam.spoktify.ui.items.model

import com.github.albertopeam.domain.models.Artist

data class ArtistItemViewModel(private val artist: Artist) {
    val id: String = artist.id
    val name: String = artist.name
    val imageUrl: String = artist.image
}