package com.github.albertopeam.spoktify.app

import android.app.Application
import com.github.albertopeam.spoktify.app.initializers.AndroidInitializer

//TODO: injection initializers
class SpoktifyApp(private val initializers: List<Initializable> = listOf(AndroidInitializer())): Application() {
    override fun onCreate() {
        super.onCreate()
        initializers.forEach { it.initialize() }
    }
}