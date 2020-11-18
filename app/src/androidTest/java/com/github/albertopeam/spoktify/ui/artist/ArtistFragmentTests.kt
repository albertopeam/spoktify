package com.github.albertopeam.spoktify.ui.artist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.spoktify.R
import com.github.albertopeam.spoktify.launchFragmentInHiltContainer
import com.github.albertopeam.spoktify.ui.artist.di.ArtistModule
import com.github.albertopeam.usecases.artists.ArtistsRepository
import com.github.albertopeam.domain.Result
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@MediumTest
@UninstallModules(ArtistModule::class)
class ArtistFragmentTests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @BindValue
    lateinit var repository: ArtistsRepository

    @Before
    fun setUp() {
        repository = mock(ArtistsRepository::class.java)
        hiltRule.inject()
    }

    @Test
    fun loading() {
        runBlocking { `when`(repository.artist("1")).then { Thread.sleep(1000) }  }

        launchFragment()

        //TODO: selected first radio
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun success() {
        runBlocking { `when`(repository.artist("1")).thenReturn(Result.Success(Artist(id = "1", "Miguel Migs", image = "url"))) }

        launchFragment()

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.item_artist_title_id)).check(matches(withText("Miguel Migs")))
    }

    @Test
    fun error() {
        runBlocking { `when`(repository.artist("1")).thenReturn(Result.Error(Exception())) }

        launchFragment()

        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.item_artist_title_id)).check(matches(withText("")))
        onView(withText("Something went wrong")).check(matches(isDisplayed()))
    }

    private fun launchFragment() {
        val bundle = ArtistFragmentArgs("1").toBundle()
        launchFragmentInHiltContainer<ArtistFragment>(bundle, R.style.AppTheme)
    }
}