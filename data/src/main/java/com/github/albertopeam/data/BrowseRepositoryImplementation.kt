package com.github.albertopeam.data

import com.github.albertopeam.data.service.BrowseService
import com.github.albertopeam.domain.Featured
import com.github.albertopeam.domain.Playlist
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.Track
import com.github.albertopeam.usecases.BrowseRepository
import java.lang.Exception
import java.util.*

internal class BrowseRepositoryImplementation(private val service: BrowseService, private val locale: Locale): BrowseRepository {
    private val country = locale.country
    private val language = locale.language + "_" + locale.country

    override suspend fun featured(): Result<Featured> {
        try {
            val response = service.featured(country = country, locale = language, timestamp = Date(), limit = 10, offset = 0)
            val playlist = response.playlist.items
                .map { it
                    it.imageUrl()?.let { image ->
                        Playlist(id = it.id, name = it.name, image = image, tracks = emptyList())
                    } ?: run {
                        null
                    }
                }
                .filterNotNull()
            return Result.Success(Featured(message = response.message, playlist = playlist))
        } catch (e: Exception) {
            return Result.Error(e)
        }

    //https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-network-error-handling-a5b98b5e5ca0
        /*can not fail???
        usar corutina en el viewMOdel y printear como funciona...
        de paso ver si haces throws que pasa y eso, pq aquí no hay result failed.... y es raro
        val  featured = Featured()
        return Result(featured, null)*/
    }
}

//https://developer.spotify.com/documentation/web-api/reference/browse/get-list-categories/
//https://developer.spotify.com/console/get-featured-playlists/?country=ES&locale=es_ES&timestamp=2020-09-12T15%3A00%3A00&limit=50&offset=0
//https://developer.spotify.com/console/get-new-releases/?country=SE