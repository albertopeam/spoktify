package com.github.albertopeam.data.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Category

interface BrowseDataSource {
    suspend fun featured(country: String, locale: String, limit: Int = 20, offset: Int = 0): Result<FeaturedPlaylists>
    suspend fun categories(country: String, locale: String, limit: Int = 50, offset: Int = 0): Result<List<Category>>
}