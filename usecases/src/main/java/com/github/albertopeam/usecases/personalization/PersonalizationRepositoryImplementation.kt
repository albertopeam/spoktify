package com.github.albertopeam.usecases.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result

class PersonalizationRepositoryImplementation(private val dataSource: PersonalizationDataSource): PersonalizationRepository {
    override suspend fun top(): Result<List<Artist>> {
        return dataSource.top(TimeRange.SHORT, 20, 0)
    }
}