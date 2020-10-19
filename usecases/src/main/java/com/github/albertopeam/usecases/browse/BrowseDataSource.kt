package com.github.albertopeam.usecases.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.Result

interface BrowseDataSource {
    suspend fun featured(country: String, locale: String, limit: Int = 10, offset: Int = 0): Result<FeaturedPlaylists>
}