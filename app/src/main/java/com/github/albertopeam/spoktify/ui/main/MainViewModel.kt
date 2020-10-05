package com.github.albertopeam.spoktify.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.browse.BrowseRepository
import com.github.albertopeam.usecases.exceptions.DataException
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(private val browseRepository: BrowseRepository,
                                                 @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val status: LiveData<String> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        when (val result = browseRepository.featured()) {
            is Result.Success -> { emit(result.data.toString()) }
            is Result.Error -> {
                when (result.exception) {
                    is DataException -> emit((result.exception as DataException).code.toString())
                    else -> emit("Something went wrong")
                }
            }
        }
        loading.postValue(false)
    }
}