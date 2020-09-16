package com.github.albertopeam.spoktify.app.initializers

import android.os.StrictMode
import com.github.albertopeam.spoktify.app.Initializable

class AndroidInitializer: Initializable {
    override fun initialize() {
        val policy = StrictMode.ThreadPolicy.Builder().detectNetwork().build()
        StrictMode.setThreadPolicy(policy)
    }

}