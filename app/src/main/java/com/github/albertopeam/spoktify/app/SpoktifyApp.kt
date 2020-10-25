package com.github.albertopeam.spoktify.app

import android.app.Application
import com.github.albertopeam.spoktify.app.di.DaggerSpoktifyApplicationComponent
import com.github.albertopeam.spoktify.app.di.SpoktifyApplicationComponent
import com.github.albertopeam.spoktify.app.initializers.Initializable
import javax.inject.Inject

//@HiltAndroidApp
class SpoktifyApp: Application() {

    @Inject
    lateinit var initializers: List<@JvmSuppressWildcards Initializable>
    val appComponent = DaggerSpoktifyApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        initializers.forEach { it.initialize() }
    }
}

//TODO: try to use dagger only and make internal the stuff like models in data or implementations otherwise make no sense to build modules
//not easy:  https://developer.android.com/training/dependency-injection/dagger-multi-module
//opt1 dagger
//opt2 factories
//TODO: mockwebserver for data
//TODO: mockito
//TODO: unit test viewModel, check threading
//TODO: navigation, custom transition PUSH + MODAL
//TODO: snkack or error in views
//TODO: player
//TODO: mainViewModel hide Mutable via inmutable