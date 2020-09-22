package com.github.albertopeam.spoktify.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.albertopeam.domain.Result
import com.github.albertopeam.usecases.BrowseRepository
import com.github.albertopeam.usecases.exceptions.DataException
import kotlinx.coroutines.Dispatchers

//TODO: inject asisted
//https://developer.android.com/training/dependency-injection/hilt-jetpack
class MainViewModel @ViewModelInject constructor(private val browseRepository: BrowseRepository): ViewModel() {
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