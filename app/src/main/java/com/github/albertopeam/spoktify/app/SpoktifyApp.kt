package com.github.albertopeam.spoktify.app

import android.app.Application
import com.github.albertopeam.spoktify.app.initializers.Initializable
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
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

//TODO: refresh? or similar
//TODO: shared cypher
//TODO: navigation, custom transition PUSH + MODAL
//TODO: clicable to go to tracks, https://developer.android.com/guide/navigation/navigation-migrate#integrate
//TODO: player
//TODO: try to use dagger only and make internal the stuff like models in data or implementations otherwise make no sense to build modules