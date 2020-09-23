package com.github.albertopeam.spoktify.ui.auth.di

import com.github.albertopeam.usecases.auth.AuthenticationRepository
import com.github.albertopeam.usecases.auth.AuthenticationRepositoryImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
object AuthenticationModule {

}