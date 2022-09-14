package com.github.albertopeam.data.artists

import com.github.albertopeam.data.model.AlbumsResponse
import com.github.albertopeam.data.model.ArtistItemResponse
import com.github.albertopeam.data.model.TracksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtistService {
    @GET("/v1/artists/{id}")
    suspend fun artist(
        @Path("id") id: String
    ): ArtistItemResponse

    @GET("/v1/artists/{id}/albums")
    suspend fun albums(
        @Path("id") id: String,
        @Query("include_groups") groups: List<String> = listOf(),
        @Query("country") country: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): AlbumsResponse

    @GET("/v1/artists/{id}/top-tracks")
    suspend fun topTracks(
        @Path("id") id: String,
        @Query("country") country: String
    ): TracksResponse
}