package com.github.albertopeam.spoktify.ui.artist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.github.albertopeam.domain.Result
import com.github.albertopeam.domain.extensions.unwrap
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.spoktify.ui.items.model.ArtistItemViewModel
import com.github.albertopeam.spoktify.util.MainCoroutineRule
import com.github.albertopeam.spoktify.util.getOrAwaitValue
import com.github.albertopeam.spoktify.util.mock
import com.github.albertopeam.usecases.artists.ArtistsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ArtistViewModelTests {
    private lateinit var sut: ArtistViewModel
    private lateinit var artistsRepositoryMock: ArtistsRepository
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        artistsRepositoryMock = mock(ArtistsRepository::class.java)
        sut = ArtistViewModel(Dispatchers.Main, artistsRepositoryMock, mock(SavedStateHandle::class.java))
    }

    @Test
    fun `when load artist then loading`() = mainCoroutineRule.runBlockingTest {
        `when`(artistsRepositoryMock.artist("1")).thenReturn(Result.Success(Artist("1", "", "")))
        var loading: Boolean? = null
        var observer: Observer<Boolean>? = null
        observer = Observer<Boolean> {
            sut.loading.removeObserver(observer.unwrap())
            loading = sut.loading.value
        }
        sut.loading.observeForever(observer)

        sut.loadArtist("1")

        assertTrue(loading.unwrap())
    }

    @Test
    fun `when load artist then observe artist`() = mainCoroutineRule.runBlockingTest {
        `when`(artistsRepositoryMock.artist("1")).thenReturn(Result.Success(Artist("1", "", "")))
        sut.artistItemViewModel.observeForever(mock())

        sut.loadArtist("1")

        assertEquals(ArtistItemViewModel(Artist("1", "", "")), sut.artistItemViewModel.getOrAwaitValue())
        assertFalse(sut.loading.getOrAwaitValue())
    }

    @Test
    fun `when load artist then observe error`() = mainCoroutineRule.runBlockingTest {
        `when`(artistsRepositoryMock.artist("1")).thenReturn(Result.Error(Exception()))
        sut.error.observeForever(mock())
        sut.loading.observeForever(mock())

        sut.loadArtist("1")

        assertEquals(sut.error.getOrAwaitValue().peekContent(), "Something went wrong")
        assertFalse(sut.loading.getOrAwaitValue())
    }
}