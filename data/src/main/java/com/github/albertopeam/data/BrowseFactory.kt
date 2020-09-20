package com.github.albertopeam.data

import com.github.albertopeam.data.service.BrowseService
import com.github.albertopeam.data.service.Constants
import com.github.albertopeam.data.service.ServiceBuilder
import com.github.albertopeam.usecases.BrowseRepository
import java.util.*

class BrowseFactory {
    companion object {
        //TODO: inject here the function to start activity
        fun make(locale: Locale, unauthorized: ((Unit) -> Unit)): BrowseRepository {
            val url = Constants.url
            val browseService = ServiceBuilder(url, unauthorized).build(BrowseService::class.java)
            return BrowseRepositoryImplementation(browseService, locale)
        }
    }
}