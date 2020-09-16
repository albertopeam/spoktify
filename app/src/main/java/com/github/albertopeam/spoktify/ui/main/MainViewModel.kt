package com.github.albertopeam.spoktify.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.github.albertopeam.data.BrowseFactory
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.BrowseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

//TODO: inject params https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc
class MainViewModel : ViewModel() {
    //TODO: coroutines from viewmodel, https://developer.android.com/topic/libraries/architecture/coroutines
    //TODO: https://proandroiddev.com/suspend-what-youre-doing-retrofit-has-now-coroutines-support-c65bd09ba067
    //TODO: koin https://medium.com/@harmittaa/retrofit-2-6-0-with-koin-and-coroutines-4ff45a4792fc

    var locale: Locale = Locale.getDefault()

    val status: LiveData<String> = liveData(Dispatchers.IO) {
        val repository = BrowseFactory.make(locale) //TODO: inject
        val result = repository.featured()
        when (result) {
            is Result.Success -> { emit(result.data.toString()) }
            is Result.Error -> { emit(result.exception.toString()) }
        }
    }
}