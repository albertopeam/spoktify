package com.github.albertopeam.data

import android.content.Context
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.albertopeam.data.auth.AuthenticationDataSourceImplementation
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthenticationDataSourceImplementationTests: TestCase() {

    private lateinit var sut: AuthenticationDataSourceImplementation
    private lateinit var sharedPreferences: SharedPreferences

    @Before
    public override fun setUp() {
        super.setUp()
        val context: Context = ApplicationProvider.getApplicationContext()
        sharedPreferences = context.getSharedPreferences("test", Context.MODE_PRIVATE)
        sut = AuthenticationDataSourceImplementation(sharedPreferences)
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        sharedPreferences.edit().clear().commit()
    }

    @Test
    fun givenEmptyPreferencesWhenGetThenReturnNull() {
        val output = sut.get()

        assertEquals(output, "")
    }

    @Test
    fun givenTokenStoredInPreferencesWhenGetThenReturnSame() {
        sharedPreferences.edit().putString("accessToken", "token").commit()

        val output = sut.get()

        assertEquals(output, "token")
    }

    @Test
    fun givenEmptyPreferencesWhenSetThenIsSaved() {
        sut.set("token")

        assertEquals(sharedPreferences.getString("accessToken", ""), "token")
    }
}