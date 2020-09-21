package com.github.albertopeam.data

import com.github.albertopeam.data.interceptors.UnauthorizedInterceptor
import com.github.albertopeam.data.service.BrowseService
import com.github.albertopeam.data.service.Constants
import com.github.albertopeam.data.service.ServiceBuilder
import com.github.albertopeam.usecases.BrowseRepository
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import java.util.*

class BrowseFactory {
    companion object {
        fun make(locale: Locale, unauthorizedChallenge: UnauthorizedChallenge): BrowseRepository {
            val url = Constants.url
            val unauthorizedInterceptor = UnauthorizedInterceptor(unauthorizedChallenge)
            val browseService = ServiceBuilder(url, unauthorizedInterceptor).build(BrowseService::class.java)
            return BrowseRepositoryImplementation(browseService, locale)
        }
    }
}