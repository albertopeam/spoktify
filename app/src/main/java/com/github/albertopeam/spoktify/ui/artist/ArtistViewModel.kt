package com.github.albertopeam.spoktify.ui.artist

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.usecases.artists.ArtistsRepository
import kotlinx.coroutines.Dispatchers

class ArtistViewModel @ViewModelInject constructor(private val artistRepository: ArtistsRepository,
                                                   @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    lateinit var id: String
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData() //TODO: snkack or error
    val artistItemViewModel: LiveData<ArtistItemViewModel> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        try {
            val artist = artistRepository.artist(id).getOrThrow()
            emit(ArtistItemViewModel(artist = artist))
        } catch (e: Exception) {
            error.postValue("Something went wrong")
        }
        loading.postValue(false)
    }
}