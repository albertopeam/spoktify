package com.github.albertopeam.spoktify.ui.main.di

import android.content.Context
import com.github.albertopeam.data.browse.BrowseDataSourceImplementation
import com.github.albertopeam.data.service.BrowseService
import com.github.albertopeam.data.service.ServiceBuilder
import com.github.albertopeam.spoktify.app.Constants
import com.github.albertopeam.spoktify.app.auth.UnauthorizedChallengeImplementation
import com.github.albertopeam.usecases.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.browse.BrowseRepository
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import com.github.albertopeam.usecases.browse.BrowseDataSource
import com.github.albertopeam.usecases.browse.BrowseRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.*

@InstallIn(ActivityComponent::class)
@Module
object MainModule {
    @Provides
    fun provideUnauthorizedChallenge(@ActivityContext context: Context): UnauthorizedChallenge {
        return UnauthorizedChallengeImplementation(context)
    }

    @Provides
    fun provideBrowseService(unauthorizedChallenge: UnauthorizedChallenge, authenticationDataSource: AuthenticationDataSource): BrowseService {
        return ServiceBuilder(Constants.url, unauthorizedChallenge, authenticationDataSource).build(BrowseService::class.java)
    }

    @Provides
    fun provideBrowseDataSource(browseService: BrowseService): BrowseDataSource {
        return BrowseDataSourceImplementation(browseService)
    }

    @Provides
    fun provideBrowseRepository(browseDataSource: BrowseDataSource, locale: Locale): BrowseRepository {
        return BrowseRepositoryImplementation(browseDataSource, locale)
    }
}