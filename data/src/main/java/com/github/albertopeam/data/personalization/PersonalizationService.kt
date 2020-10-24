package com.github.albertopeam.data.personalization

import com.github.albertopeam.data.model.FeaturedResponse
import com.github.albertopeam.data.model.TopArtistsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonalizationService {
    @GET("/v1/me/top/artists")
    suspend fun top(
        @Query("time_range") timeRange: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): TopArtistsResponse
}