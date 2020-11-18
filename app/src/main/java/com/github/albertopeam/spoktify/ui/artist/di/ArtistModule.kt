package com.github.albertopeam.spoktify.ui.artist.di

import com.github.albertopeam.data.artists.ArtistDataSourceImplementation
import com.github.albertopeam.data.artists.ArtistService
import com.github.albertopeam.data.service.ServiceBuilder
import com.github.albertopeam.spoktify.app.Constants
import com.github.albertopeam.usecases.artists.ArtistDataSource
import com.github.albertopeam.usecases.artists.ArtistsRepository
import com.github.albertopeam.usecases.artists.ArtistsRepositoryImplementation
import com.github.albertopeam.usecases.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object ArtistModule {
    @Provides
    fun provideArtistService(unauthorizedChallenge: UnauthorizedChallenge, authenticationDataSource: AuthenticationDataSource): ArtistService {
        return ServiceBuilder(Constants.url, unauthorizedChallenge, authenticationDataSource).build(
            ArtistService::class.java)
    }

    @Provides
    fun provideArtistDataSource(artistService: ArtistService): ArtistDataSource {
        return ArtistDataSourceImplementation(artistService)
    }

    @Provides
    fun provideArtistRepository(artistDataSource: ArtistDataSource): ArtistsRepository {
        return ArtistsRepositoryImplementation(artistDataSource)
    }
}