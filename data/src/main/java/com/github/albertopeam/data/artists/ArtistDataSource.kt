package com.github.albertopeam.data.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Album
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.models.Track

interface ArtistDataSource {
    suspend fun artist(id: String): Result<Artist>
    suspend fun albums(id: String): Result<List<Album>>
    suspend fun topTracks(id: String): Result<List<Track>>
}