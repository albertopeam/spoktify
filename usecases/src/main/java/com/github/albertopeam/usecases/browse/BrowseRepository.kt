package com.github.albertopeam.usecases.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.Result

interface BrowseRepository {
    suspend fun featured(): Result<FeaturedPlaylists>
}