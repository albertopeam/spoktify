package com.github.albertopeam.usecases.browse

import com.github.albertopeam.usecases.auth.AuthenticationRepository

class FeaturedUseCaseImplementation(private val browseRepository: BrowseRepository): FeaturedUseCase {
    override suspend fun featured(): Featured {
        TODO("pending to implement")
        //return browseRepository.featured()
    }
}