package com.github.albertopeam.spoktify.ui.artist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.albertopeam.spoktify.R
import com.github.albertopeam.spoktify.launchFragmentInHiltContainer
import com.github.albertopeam.usecases.artists.ArtistsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ArtistFragmentTests {
    //https://developer.android.com/training/dependency-injection/hilt-testing
    //https://codelabs.developers.google.com/codelabs/android-hilt#9
    //https://codelabs.developers.google.com/codelabs/android-hilt#9

    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    //@Inject
    //lateinit var repository: ArtistsRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        val bundle = ArtistFragmentArgs("1").toBundle()
        launchFragmentInHiltContainer<ArtistFragment>(bundle, R.style.AppTheme)
    }

    @Test
    fun loading() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }
}