package com.github.albertopeam.data.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.personalization.TimeRange

interface PersonalizationDataSource {
    suspend fun top(timeRange: TimeRange, limit: Int, offset: Int): Result<List<Artist>>
}