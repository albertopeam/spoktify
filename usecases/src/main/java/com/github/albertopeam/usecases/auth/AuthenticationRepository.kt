package com.github.albertopeam.usecases.auth

interface AuthenticationRepository {
    val authenticationUrl: String
    val accessToken: String
    fun storeIfContainsAccessToken(callbackUrl: String): Boolean
}