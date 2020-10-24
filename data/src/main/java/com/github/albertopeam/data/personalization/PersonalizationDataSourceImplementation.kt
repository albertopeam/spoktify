package com.github.albertopeam.data.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.exceptions.DataException
import com.github.albertopeam.usecases.personalization.PersonalizationDataSource
import com.github.albertopeam.usecases.personalization.TimeRange
import retrofit2.HttpException

class PersonalizationDataSourceImplementation(private val service: PersonalizationService): PersonalizationDataSource {
    override suspend fun top(timeRange: TimeRange, limit: Int, offset: Int): Result<List<Artist>> {
        return try {
            val response = service.top(timeRange.value(), 20, 0)
            val artists = response.items.mapNotNull { it
                it.imageUrl()?.let { image ->
                    Artist(
                        it.id,
                        it.name,
                        image
                    )
                } ?: run {
                    null
                }
            }
            Result.Success(artists)
        } catch (e: HttpException) {
            Result.Error(DataException(code = e.code(), message = e.message()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

