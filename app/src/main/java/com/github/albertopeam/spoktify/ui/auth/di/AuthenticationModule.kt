package com.github.albertopeam.spoktify.ui.auth.di

import com.github.albertopeam.spoktify.BuildConfig
import com.github.albertopeam.usecases.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.auth.AuthenticationRepository
import com.github.albertopeam.usecases.auth.AuthenticationRepositoryImplementation
import dagger.Module
import dagger.Provides

//@InstallIn(ActivityComponent::class)
@Module
object AuthenticationModule {
    @Provides
    fun provideAuthRepository(dataSource: AuthenticationDataSource): AuthenticationRepository {
        return AuthenticationRepositoryImplementation(BuildConfig.SPOKTIFY_CLIENT_ID, BuildConfig.SPOKTIFY_REDIRECT_URI, dataSource)
    }
}