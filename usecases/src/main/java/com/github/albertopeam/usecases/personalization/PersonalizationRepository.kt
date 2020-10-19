package com.github.albertopeam.usecases.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result

interface PersonalizationRepository {
    suspend fun top(): Result<List<Artist>>
}