package com.github.albertopeam.data

import com.github.albertopeam.data.artists.ArtistDataSourceImplementation
import com.github.albertopeam.data.artists.ArtistService
import com.github.albertopeam.data.service.ServiceBuilder
import com.github.albertopeam.data.utils.MockResponseFileReader
import com.github.albertopeam.domain.getOrThrow
import com.github.albertopeam.domain.models.Artist
import com.github.albertopeam.data.auth.AuthenticationDataSource
import com.github.albertopeam.usecases.auth.UnauthorizedChallenge
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify


class ArtistsDataSourceImplementationTests: TestCase() {
    private lateinit var sut: ArtistDataSourceImplementation
    private lateinit var server: MockWebServer
    private lateinit var unauthorizedChallengeSpy: UnauthorizedChallenge
    private lateinit var authenticationDataSourceMock: AuthenticationDataSource

    override fun setUp() {
        super.setUp()
        unauthorizedChallengeSpy = Mockito.spy(UnauthorizedChallenge::class.java)
        authenticationDataSourceMock = Mockito.mock(AuthenticationDataSource::class.java)
        server = MockWebServer()
        server.start()
        val service = ServiceBuilder(server.url("/v1/").toString(), unauthorizedChallengeSpy, authenticationDataSourceMock, useLoginInterceptor = false)
            .build(ArtistService::class.java)
        sut = ArtistDataSourceImplementation(service)
    }

    override fun tearDown() {
        super.tearDown()
        server.shutdown()
    }

    @Test
    fun `test given authenticated when get artist for id then match success data`() = runBlocking {
        Mockito.`when`(authenticationDataSourceMock.get()).thenReturn("app_token")
        server.enqueue(MockResponse()
            .setResponseCode(200)
            .setBody(MockResponseFileReader("api_artist_by_id_success.json").read()))

        val result = sut.artist("1").getOrThrow()

        val expected = Artist(id = "1", name = "Band of Horses", image = "https://i.scdn.co/image/0f9a5013134de288af7d49a962417f4200539b47")
        assertEquals(result, expected)
        verify(unauthorizedChallengeSpy, never()).challenge()
    }
}