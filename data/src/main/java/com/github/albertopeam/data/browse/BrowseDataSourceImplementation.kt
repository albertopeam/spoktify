package com.github.albertopeam.data.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.models.Playlist
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.extensions.iso8601
import com.github.albertopeam.domain.models.Category
import com.github.albertopeam.usecases.exceptions.DataException
import retrofit2.HttpException
import java.util.*

class BrowseDataSourceImplementation(private val service: BrowseService): BrowseDataSource {
    override suspend fun featured(
        country: String,
        locale: String,
        limit: Int,
        offset: Int
    ): Result<FeaturedPlaylists> {
        return try {
            val response = service.featured(country = country, locale = locale, timestamp = Date().iso8601(), limit = limit, offset = offset)
            val playlist = response.playlist.items.mapNotNull { it
                it.imageUrl()?.let { image ->
                    Playlist(
                        id = it.id,
                        name = it.name,
                        image = image,
                        tracks = emptyList()
                    )
                } ?: run {
                    null
                }
            }
            Result.Success(
                FeaturedPlaylists(
                    message = response.message,
                    playlist = playlist
                )
            )
        } catch (e: HttpException) {
            Result.Error(DataException(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun categories(
        country: String,
        locale: String,
        limit: Int,
        offset: Int
    ): Result<List<Category>> {
        return try {
            val response = service.categories(country = country, locale = locale, limit = limit, offset = offset)
            val categories = response.categories.items.mapNotNull { it
                it.imageUrl()?.let { image ->
                    Category(
                        id = it.id,
                        name = it.name,
                        image = image
                    )
                } ?: run {
                    null
                }
            }
            Result.Success(categories)
        } catch (e: HttpException) {
            Result.Error(DataException(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}