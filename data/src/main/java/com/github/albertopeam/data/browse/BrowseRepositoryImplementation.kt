package com.github.albertopeam.data.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.domain.models.Category
import com.github.albertopeam.usecases.browse.BrowseRepository
import java.lang.Exception
import java.util.*

class BrowseRepositoryImplementation(private val dataSource: BrowseDataSource, locale: Locale):
    BrowseRepository {
    private val country = locale.country
    private val language = locale.language + "_" + locale.country

    override suspend fun featured(): Result<FeaturedPlaylists> {
        return dataSource.featured(country, language)
    }

    override suspend fun categories(): Result<List<Category>> {
        val result = dataSource.categories(country, language)
        return try {
            val categories = result.getOrThrow()
            val sorted = categories.sortedBy {  it.name }
            Result.Success(sorted)
        } catch (e: Exception) {
            result
        }
    }
}
