package com.github.albertopeam.usecases.browse

import com.github.albertopeam.domain.models.FeaturedPlaylists
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.models.Category

interface BrowseRepository {
    suspend fun featured(): Result<FeaturedPlaylists>
    suspend fun categories(): Result<List<Category>>
}