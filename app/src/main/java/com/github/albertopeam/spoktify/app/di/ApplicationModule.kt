package com.github.albertopeam.spoktify.app.di

import android.content.Context
import androidx.core.os.ConfigurationCompat
import com.github.albertopeam.spoktify.app.initializers.Initializable
import com.github.albertopeam.spoktify.app.initializers.AndroidInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Singleton


//TODO: hilt multi module
// https://developer.android.com/training/dependency-injection/hilt-multi-module
@InstallIn(ApplicationComponent::class)
@Module
object SpoktifyModule {
    @Provides
    @Singleton
    fun provideLocale(@ApplicationContext appContext: Context): Locale {
        return ConfigurationCompat.getLocales(appContext.resources.configuration)[0]
    }

    @Provides
    @Singleton
    fun provideInitializers(): List<Initializable> {
        return listOf(AndroidInitializer())
    }
}