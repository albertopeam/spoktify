package com.github.albertopeam.data.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.usecases.artists.ArtistsRepository

class ArtistsRepositoryImplementation(private val dataSource: ArtistDataSource): ArtistsRepository {
    override suspend fun artist(id: String): Result<Artist> {
        return dataSource.artist(id)
    }
}