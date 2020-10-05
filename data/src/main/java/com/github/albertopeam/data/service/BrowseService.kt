package com.github.albertopeam.data.service

import com.github.albertopeam.data.model.FeaturedResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface BrowseService {
    @GET("/v1/browse/featured-playlists")
    suspend fun featured(
        @Query("country") country: String,
        @Query("locale") locale: String,
        @Query("timestamp") timestamp: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): FeaturedResponse
}