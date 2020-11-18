package com.github.albertopeam.spoktify.ui.artist

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.spoktify.app.di.DispatcherIO
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.usecases.artists.ArtistsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class ArtistViewModel @ViewModelInject constructor(
    @DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val artistRepository: ArtistsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    private lateinit var id: String
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()
    private val _artistItemViewModel = MutableLiveData<ArtistItemViewModel>()
    private val _types = MutableLiveData<List<Boolean>>()
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<String> = _error
    val artistItemViewModel: LiveData<ArtistItemViewModel> = _artistItemViewModel
    val types: LiveData<List<Boolean>> = _types

    init {
        _types.value = listOf(true, false, false)
    }

    fun loadArtist(withId: String) {
        id = withId
        _loading.postValue(true)
        viewModelScope.launch(dispatcher) {
            try {
                val artist = artistRepository.artist(id).getOrThrow()
                _artistItemViewModel.postValue(ArtistItemViewModel(artist))
            } catch (e: Exception) {
                _error.postValue("Something went wrong")
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun selected(type: Type) {
        println(type.toString())
    }

    enum class Type {
        ALBUMS, TOP, RELATED
    }
}

