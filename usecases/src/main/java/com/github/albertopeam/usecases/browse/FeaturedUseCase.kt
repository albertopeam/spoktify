package com.github.albertopeam.usecases.browse

interface FeaturedUseCase {
    suspend fun featured(): Featured
}