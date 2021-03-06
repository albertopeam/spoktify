package com.github.albertopeam.spoktify.app.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.core.os.ConfigurationCompat
import com.github.albertopeam.data.auth.AuthenticationDataSourceImplementation
import com.github.albertopeam.spoktify.app.initializers.AndroidInitializer
import com.github.albertopeam.spoktify.app.initializers.Initializable
import com.github.albertopeam.data.auth.AuthenticationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    @Provides
    @Singleton
    fun provideInitializers(): List<Initializable> {
        return listOf(AndroidInitializer())
    }

    @Provides
    @Singleton
    fun provideAuthenticationDataSource(@ApplicationContext appContext: Context): AuthenticationDataSource {
        return AuthenticationDataSourceImplementation(
            appContext.getSharedPreferences(
                "auth",
                MODE_PRIVATE
            )
        )
    }

    @Provides
    @Singleton
    @DispatcherIO
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}