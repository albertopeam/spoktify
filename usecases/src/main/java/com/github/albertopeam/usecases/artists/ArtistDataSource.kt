package com.github.albertopeam.usecases.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Artist

interface ArtistDataSource {
    suspend fun artist(id: String): Result<Artist>
}