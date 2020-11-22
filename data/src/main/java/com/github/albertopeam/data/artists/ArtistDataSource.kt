package com.github.albertopeam.data.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Artist

interface ArtistDataSource {
    suspend fun artist(id: String): Result<Artist>
}