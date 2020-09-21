package com.github.albertopeam.spoktify.app.di

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.github.albertopeam.data.BrowseFactory
import com.github.albertopeam.spoktify.app.SpoktifyApp
import com.github.albertopeam.spoktify.ui.main.MainFragment
import com.github.albertopeam.usecases.BrowseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object SpoktifyModule {
    @Provides
    @Singleton
    fun provideLocale(@ApplicationContext appContext: Context): Locale {
        return ConfigurationCompat.getLocales(appContext.resources.configuration)[0]
    }
}