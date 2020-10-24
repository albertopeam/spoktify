package com.github.albertopeam.data.artists

import com.github.albertopeam.data.model.ArtistItemResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {
    @GET("/v1/artists/{id}")
    suspend fun artist(
        @Path("id") id: String
    ): ArtistItemResponse
}