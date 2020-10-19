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

//TODO: more horizontal stuff
//TODO: clicable to go to tracks
//TODO: player
//TODO: try to use dagger only and make internal the stuff like models in data or implementations otherwise make no sense to build modules
//TODO: mockwebserver for data
//TODO: mockito
//TODO: mainViewModel hide Mutable via inmutable
//TODO: unit test viewModel, check threading