package com.github.albertopeam.spoktify.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.Result
import com.github.albertopeam.spoktify.ui.items.PlaylistItemViewModel
import com.github.albertopeam.usecases.browse.BrowseRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel @ViewModelInject constructor(private val browseRepository: BrowseRepository,
                                                 @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    //TODO: hide Mutable via inmutable
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val playlists: LiveData<List<PlaylistItemViewModel>> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        when (val result = browseRepository.featured()) {
            is Result.Success -> {  emit(result.data.playlist.map { PlaylistItemViewModel(it) })  }
            is Result.Error -> {  error.postValue("Something went wrong") }
        }
        loading.postValue(false)
    }
}