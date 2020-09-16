package com.github.albertopeam.usecases

import com.github.albertopeam.domain.Featured
import com.github.albertopeam.domain.Result

interface BrowseRepository {
    suspend fun featured(): Result<Featured>
}