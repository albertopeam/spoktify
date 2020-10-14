package com.github.albertopeam.usecases.auth

import java.util.regex.Matcher
import java.util.regex.Pattern


class AuthenticationRepositoryImplementation(
    private val clientId: String,
    private val redirectUri: String,
    private val dataSource: AuthenticationDataSource,
): AuthenticationRepository {

    override val authenticationUrl: String
        get() {
            val responseType = "token"
            val scopes = arrayOf("user-read-email", "user-read-private", "user-top-read").joinToString(separator = "%20")
            return "https://accounts.spotify.com/authorize?client_id=$clientId&response_type=$responseType&redirect_uri=$redirectUri&scope=$scopes"
        }
    override val accessToken: String = dataSource.get()

    override fun storeIfContainsAccessToken(callbackUrl: String): Boolean {
        val regex = "access_token=([^&]+)"
        val matcher: Matcher = Pattern.compile(regex).matcher(callbackUrl)
        if (matcher.find()) {
            val result = matcher.group()
            val accessToken = result.replace("access_token=", "")
            dataSource.set(accessToken)
            return true
        }
        return false
    }
}