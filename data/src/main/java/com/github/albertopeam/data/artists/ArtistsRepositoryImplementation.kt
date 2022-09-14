package com.github.albertopeam.data.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Album
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.models.Track
import com.github.albertopeam.usecases.artists.ArtistsRepository

class ArtistsRepositoryImplementation(private val dataSource: ArtistDataSource): ArtistsRepository {
    override suspend fun artist(id: String): Result<Artist> {
        return dataSource.artist(id)
    }

    override suspend fun albums(id: String): Result<List<Album>> {
        TODO("Not yet implemented")
    }

    override suspend fun topTracks(id: String): Result<List<Track>> {
        TODO("Not yet implemented")
    }
}