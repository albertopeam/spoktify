package com.github.albertopeam.spoktify.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.spoktify.ui.items.model.CategoryItemViewModel
import com.github.albertopeam.spoktify.ui.items.model.PlaylistItemViewModel
import com.github.albertopeam.spoktify.ui.items.model.Section
import com.github.albertopeam.usecases.browse.BrowseRepository
import com.github.albertopeam.usecases.personalization.PersonalizationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class MainViewModel @ViewModelInject constructor(private val browseRepository: BrowseRepository,
                                                 private val personalizationRepository: PersonalizationRepository,
                                                 @Assisted private val savedStateHandle: SavedStateHandle): ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()
    val sections: LiveData<List<Section>> = liveData(Dispatchers.IO) {
        coroutineScope {
            loading.postValue(true)
            try {
                val featuredDeferred = async(Dispatchers.IO) { browseRepository.featured() }
                val categoriesDeferred = async(Dispatchers.IO) { browseRepository.categories() }
                val topDeferred = async(Dispatchers.IO) {  personalizationRepository.top() }
                val featureResult = featuredDeferred.await()
                val categoriesResult = categoriesDeferred.await()
                val topResult = topDeferred.await()
                val featured = featureResult.getOrThrow().playlist.map { PlaylistItemViewModel(it) }
                val categories = categoriesResult.getOrThrow().map { CategoryItemViewModel(it) }
                val top = topResult.getOrThrow().map { ArtistItemViewModel(it) }
                emit(listOf(Section(title = "Featured", featured), Section(title = "Top for you", top), Section(title = "Categories", categories)))
            } catch (e: Exception) {
                error.postValue("Something went wrong")
            }
            loading.postValue(false)
        }
    }
}