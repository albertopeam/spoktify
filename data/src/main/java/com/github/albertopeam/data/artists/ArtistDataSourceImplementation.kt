package com.github.albertopeam.data.artists

import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.usecases.exceptions.DataException
import retrofit2.HttpException

class ArtistDataSourceImplementation(private val service: ArtistService): ArtistDataSource {
    override suspend fun artist(id: String): Result<Artist> {
        return try {
            val response = service.artist(id)
            Result.Success(Artist(response.id, response.name, response.imageUrl() ?: ""))
        } catch (e: HttpException) {
            Result.Error(DataException(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}