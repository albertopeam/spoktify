package com.github.albertopeam.spoktify.ui.main.di

import android.content.Context
import com.github.albertopeam.data.BrowseFactory
import com.github.albertopeam.spoktify.app.auth.UnauthorizedChallengeImplementation
import com.github.albertopeam.usecases.BrowseRepository
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
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
    fun provideBrowseRepository(locale: Locale, unauthorizedChallenge: UnauthorizedChallenge): BrowseRepository {
        return BrowseFactory.make(locale, unauthorizedChallenge)
    }
}

/* TODO:
mirar de meter las lib y lo de los módulos hacerlo al acabar con el /app
lib viewmodel hilt -> ant leiva
modulos con hilt?
 */