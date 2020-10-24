package com.github.albertopeam.usecases.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Artist

class ArtistsRepositoryImplementation(private val dataSource: ArtistDataSource): ArtistsRepository {
    override suspend fun artist(id: String): Result<Artist> {
        return dataSource.artist(id)
    }
}