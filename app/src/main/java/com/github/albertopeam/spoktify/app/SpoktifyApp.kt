package com.github.albertopeam.spoktify.app

import android.app.Application
import com.github.albertopeam.spoktify.app.initializers.Initializable
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SpoktifyApp: Application() {

    @Inject
    lateinit var initializers: List<@JvmSuppressWildcards Initializable>

    override fun onCreate() {
        super.onCreate()
        initializers.forEach { it.initialize() }
    }
}