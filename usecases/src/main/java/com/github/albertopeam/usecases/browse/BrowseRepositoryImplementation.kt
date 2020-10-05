package com.github.albertopeam.usecases.browse

import com.github.albertopeam.domain.FeaturedPlaylists
import com.github.albertopeam.domain.Result
import java.util.*

class BrowseRepositoryImplementation(private val dataSource: BrowseDataSource, locale: Locale):
    BrowseRepository {
    private val country = locale.country
    private val language = locale.language + "_" + locale.country

    override suspend fun featured(): Result<FeaturedPlaylists> {
        return dataSource.featured(country, language)
    }
}

//TODO: spotify api
//https://developer.spotify.com/documentation/web-api/reference/browse/get-list-categories/
//https://developer.spotify.com/console/get-featured-playlists/?country=ES&locale=es_ES&timestamp=2020-09-12T15%3A00%3A00&limit=50&offset=0
//https://developer.spotify.com/console/get-new-releases/?country=SE
