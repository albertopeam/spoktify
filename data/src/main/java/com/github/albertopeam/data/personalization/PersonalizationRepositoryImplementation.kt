package com.github.albertopeam.data.personalization

import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.personalization.PersonalizationRepository
import com.github.albertopeam.usecases.personalization.TimeRange

class PersonalizationRepositoryImplementation(private val dataSource: PersonalizationDataSource):
    PersonalizationRepository {
    override suspend fun top(): Result<List<Artist>> {
        return dataSource.top(TimeRange.SHORT, 20, 0)
    }
}