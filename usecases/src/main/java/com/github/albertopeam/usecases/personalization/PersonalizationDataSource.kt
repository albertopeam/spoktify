package com.github.albertopeam.usecases.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result

interface PersonalizationDataSource {
    suspend fun top(timeRange: TimeRange, limit: Int, offset: Int): Result<List<Artist>>
}