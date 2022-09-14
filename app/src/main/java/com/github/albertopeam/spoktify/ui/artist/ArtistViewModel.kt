package com.github.albertopeam.spoktify.ui.artist

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.domain.map
import com.github.albertopeam.domain.models.Album
import com.github.albertopeam.domain.models.Track
import com.github.albertopeam.spoktify.app.di.DispatcherIO
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.spoktify.ui.models.Event
import com.github.albertopeam.usecases.artists.ArtistsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class ArtistViewModel @ViewModelInject constructor(
    @DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val artistRepository: ArtistsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    private lateinit var id: String
    private val _loading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<Event<String>>()
    private val _artistItemViewModel = MutableLiveData<ArtistItemViewModel>()
    private val _types = MutableLiveData<List<Boolean>>()
    val loading: LiveData<Boolean> = _loading
    val error: LiveData<Event<String>> = _error
    val artistItemViewModel: LiveData<ArtistItemViewModel> = _artistItemViewModel
    val types: LiveData<List<Boolean>> = _types

    init {
        _types.value = listOf(true, false, false)
    }

    fun loadArtist(withId: String) {
        id = withId
        loadArtist()
        loadArtistData()
    }

    fun selected(type: Type) {
        println(type.toString())
    }

    private fun loadArtist() {
        _loading.postValue(true)
        viewModelScope.launch(dispatcher) {
            try {
                val artist = artistRepository.artist(id).getOrThrow()
                _artistItemViewModel.postValue(ArtistItemViewModel(artist))
            } catch (e: Exception) {
                _error.postValue(Event("Something went wrong"))
            } finally {
                _loading.postValue(false)
            }
        }
    }

    private fun loadArtistData() {
        val albums: Flow<Result<List<Album>>> = flow {
            val map: Map<String, Result<List<Any>>> = artistRepository.albums(id)
                .map { mapOf(Pair("albums", it)) }
        }
        val topTracks: Flow<Result<List<Track>>> = flow { artistRepository.topTracks(id) }
        val zip = albums.zip(topTracks)
    }

    enum class Type {
        ALBUMS, TOP, RELATED
    }
}

